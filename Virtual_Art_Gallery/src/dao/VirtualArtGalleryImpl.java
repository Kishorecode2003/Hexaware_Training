package src.dao;

import src.entity.Artwork;
import src.entity.Artist;
import src.entity.Gallery;
import src.util.DBConnUtil;
import src.exception.ArtWorkNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualArtGalleryImpl implements IVirtualArtGallery {
    private static Connection conn;

    public VirtualArtGalleryImpl() {
        conn = DBConnUtil.getConnection("resources/db.properties");
    }


    // --- Artwork Management ---
    @Override
    public boolean addArtwork(Artwork artwork) {
        try {
            String query = "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, artwork.getTitle());
            stmt.setString(2, artwork.getDescription());
            stmt.setString(3, artwork.getCreationDate());
            stmt.setString(4, artwork.getMedium());
            stmt.setString(5, artwork.getImageUrl());
            stmt.setInt(6, artwork.getArtistId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        try {
            String query = "UPDATE Artwork SET Title=?, Description=?, CreationDate=?, Medium=?, ImageURL=?, ArtistID=? WHERE ArtworkID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, artwork.getTitle());
            stmt.setString(2, artwork.getDescription());
            stmt.setString(3, artwork.getCreationDate());
            stmt.setString(4, artwork.getMedium());
            stmt.setString(5, artwork.getImageUrl());
            stmt.setInt(6, artwork.getArtistId());
            stmt.setInt(7, artwork.getArtworkId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeArtwork(int artworkId) {
        try {
            String query = "DELETE FROM Artwork WHERE ArtworkID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, artworkId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Artwork getArtworkById(int artworkId) {
        try {
            String query = "SELECT * FROM Artwork WHERE ArtworkID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, artworkId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Artwork(artworkId, rs.getString("Title"), rs.getString("Description"),
                        rs.getString("CreationDate"), rs.getString("Medium"), rs.getString("ImageURL"),
                        rs.getInt("ArtistID"));
            } else {
                throw new ArtWorkNotFoundException("Artwork not found");
            }
        } catch (SQLException | ArtWorkNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        try {
            String query = "SELECT * FROM Artwork WHERE Title LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artworks.add(new Artwork(rs.getInt("ArtworkID"), rs.getString("Title"), rs.getString("Description"),
                        rs.getString("CreationDate"), rs.getString("Medium"), rs.getString("ImageURL"),
                        rs.getInt("ArtistID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    // --- Favorites ---
    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        try {
            String query = "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, artworkId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        try {
            String query = "DELETE FROM User_Favorite_Artwork WHERE UserID=? AND ArtworkID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, artworkId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) {
        List<Artwork> artworks = new ArrayList<>();
        try {
            String query = "SELECT a.* FROM Artwork a JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID WHERE ufa.UserID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artworks.add(new Artwork(rs.getInt("ArtworkID"), rs.getString("Title"), rs.getString("Description"),
                        rs.getString("CreationDate"), rs.getString("Medium"), rs.getString("ImageURL"),
                        rs.getInt("ArtistID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    // --- Gallery Management ---
    @Override
    public boolean addGallery(Gallery gallery) {
        try {
            String query = "INSERT INTO Gallery (Name, Description, Location, Curator, OpeningHours) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, gallery.getName());
            stmt.setString(2, gallery.getDescription());
            stmt.setString(3, gallery.getLocation());
            stmt.setInt(4, gallery.getCurator());
            stmt.setString(5, gallery.getOpeningHours());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGallery(Gallery gallery) {
        try {
            String query = "UPDATE Gallery SET Name=?, Description=?, Location=?, Curator=?, OpeningHours=? WHERE GalleryID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, gallery.getName());
            stmt.setString(2, gallery.getDescription());
            stmt.setString(3, gallery.getLocation());
            stmt.setInt(4, gallery.getCurator());
            stmt.setString(5, gallery.getOpeningHours());
            stmt.setInt(6, gallery.getGalleryId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeGallery(int galleryId) {
        try {
            String query = "DELETE FROM Gallery WHERE GalleryID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, galleryId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Gallery> searchGalleries(String keyword) {
        List<Gallery> galleries = new ArrayList<>();
        try {
            String query = "SELECT * FROM Gallery WHERE Name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                galleries.add(new Gallery(rs.getInt("GalleryID"), rs.getString("Name"),
                        rs.getString("Description"), rs.getString("Location"),
                        rs.getInt("Curator"), rs.getString("OpeningHours")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galleries;
    }

    // --- Artist Management ---
    @Override
    public boolean addArtist(Artist artist) {
        try {
            String query = "INSERT INTO Artist (ArtistID, Name, Biography, BirthDate, Nationality, Website, ContactInfo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, artist.getArtistId());
            stmt.setString(2, artist.getName());
            stmt.setString(3, artist.getBiography());
            stmt.setString(4, artist.getBirthDate());
            stmt.setString(5, artist.getNationality());
            stmt.setString(6, artist.getWebsite());
            stmt.setString(7, artist.getContactInfo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
