package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.dao.VirtualArtGalleryImpl;
import src.entity.Artist;
import src.entity.Artwork;
import src.entity.Gallery;
import src.exception.UserNotFoundException;

public class VirtualArtGalleryTests {

    private VirtualArtGalleryImpl galleryService;

    @BeforeEach
    public void setup() {
        galleryService = new VirtualArtGalleryImpl();

        Artist artist = new Artist(
                0,
                "Vincent van Gogh",
                "Dutch post-impressionist painter",
                "1853-03-30",
                "Dutch",
                "https://www.vangogh.com",
                "9876543210"
        );
        galleryService.addArtist(artist);

        Artwork artwork = new Artwork(
                101,
                "Starry Night",
                "A masterpiece",
                "1889-06-01",
                "Oil on canvas",
                "url_to_image",
                1
        );
        galleryService.addArtwork(artwork);

        Gallery gallery = new Gallery(
                201,
                "Modern Art Hub",
                "Modern collection",
                "NYC",
                1,
                "9AM-6PM"
        );
        galleryService.addGallery(gallery);
    }

    @Test
    public void testAddArtwork() {
        Artwork artwork = new Artwork(
                102,
                "Sunflowers",
                "Bright and beautiful",
                "1888-01-01",
                "Oil on canvas",
                "url_to_image_2",
                1
        );
        boolean result = galleryService.addArtwork(artwork);
        assertTrue(result);
    }

    @Test
    public void testSearchArtworks() {
        List<Artwork> artworks = galleryService.searchArtworks("Starry");
        assertNotNull(artworks);
        assertFalse(artworks.isEmpty());
    }

    @Test
    public void testGetUserFavorites() throws UserNotFoundException {
        List<Artwork> favorites = galleryService.getUserFavoriteArtworks(1);
        assertNotNull(favorites);
    }

    @Test
    public void testSearchGalleries() {
        List<Gallery> galleries = galleryService.searchGalleries("Modern");
        assertNotNull(galleries);
        assertFalse(galleries.isEmpty());
    }
}
