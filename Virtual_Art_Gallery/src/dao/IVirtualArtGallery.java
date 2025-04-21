package src.dao;

import src.entity.Artwork;
import src.entity.Gallery;
import src.entity.Artist;

import java.util.List;

public interface IVirtualArtGallery {
    boolean addArtwork(Artwork artwork);
    boolean updateArtwork(Artwork artwork);
    boolean removeArtwork(int artworkId);
    Artwork getArtworkById(int artworkId);
    List<Artwork> searchArtworks(String keyword);

    boolean addArtworkToFavorite(int userId, int artworkId);
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    List<Artwork> getUserFavoriteArtworks(int userId);

    boolean addGallery(Gallery gallery);
    boolean updateGallery(Gallery gallery);
    boolean removeGallery(int galleryId);
    List<Gallery> searchGalleries(String keyword);

    boolean addArtist(Artist artist);

}
