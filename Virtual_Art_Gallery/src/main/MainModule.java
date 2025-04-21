package src.main;

import src.dao.VirtualArtGalleryImpl;
import src.entity.Artwork;
import src.entity.Artist;
import src.entity.Gallery;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VirtualArtGalleryImpl galleryService = new VirtualArtGalleryImpl();

        while (true) {
            System.out.println("\n--- Virtual Art Gallery Menu ---");
            System.out.println("1. Add Artwork");
            System.out.println("2. Search Artwork");
            System.out.println("3. Register Artist");
            System.out.println("4. Create Gallery");
            System.out.println("5. Modify Gallery");
            System.out.println("6. Delete Gallery");
            System.out.println("7. Find Galleries");
            System.out.println("8. Mark Favorite");
            System.out.println("9. Unmark Favorite");
            System.out.println("10. Show Favorites");
            System.out.println("11. Exit");
            System.out.print("Select an option: ");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    try {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Creation Date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Medium: ");
                        String medium = scanner.nextLine();
                        System.out.print("Image URL: ");
                        String imageUrl = scanner.nextLine();
                        System.out.print("Artist ID: ");
                        int artistId = scanner.nextInt();
                        scanner.nextLine();

                        Artwork art = new Artwork(0, title, description, date, medium, imageUrl, artistId);
                        boolean inserted = galleryService.addArtwork(art);
                        System.out.println(inserted ? "Artwork saved successfully." : "Failed to save. Check Artist ID.");
                    } catch (Exception e) {
                        System.out.println("Error while adding artwork.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Search by keyword: ");
                    String keyword = scanner.nextLine();
                    List<Artwork> artworks = galleryService.searchArtworks(keyword);
                    if (artworks.isEmpty()) {
                        System.out.println("No matching artworks found.");
                    } else {
                        System.out.println("Results:");
                        for (Artwork a : artworks) {
                            System.out.println("---------------");
                            System.out.println("ID: " + a.getArtworkId());
                            System.out.println("Title: " + a.getTitle());
                            System.out.println("Description: " + a.getDescription());
                            System.out.println("Date: " + a.getCreationDate());
                            System.out.println("Medium: " + a.getMedium());
                            System.out.println("Image: " + a.getImageUrl());
                            System.out.println("Artist ID: " + a.getArtistId());
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Artist ID: ");
                        int aid = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Biography: ");
                        String bio = scanner.nextLine();
                        System.out.print("Birth Date (YYYY-MM-DD): ");
                        String dob = scanner.nextLine();
                        System.out.print("Nationality: ");
                        String nation = scanner.nextLine();
                        System.out.print("Website: ");
                        String web = scanner.nextLine();
                        System.out.print("Contact Info: ");
                        String contact = scanner.nextLine();

                        Artist artist = new Artist(aid, name, bio, dob, nation, web, contact);
                        boolean result = galleryService.addArtist(artist);
                        System.out.println(result ? "Artist registered." : "Artist could not be registered.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Failed to register artist.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Gallery Name: ");
                    String gName = scanner.nextLine();
                    System.out.print("Description: ");
                    String gDesc = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Curator ID: ");
                    int curatorId = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Opening Hours: ");
                    String openTime = scanner.nextLine();

                    Gallery newGallery = new Gallery(0, gName, gDesc, location, curatorId, openTime);
                    boolean created = galleryService.addGallery(newGallery);
                    System.out.println(created ? "Gallery created." : "Creation failed.");
                    break;

                case 5:
                    System.out.print("Gallery ID to update: ");
                    int gid = scanner.nextInt(); scanner.nextLine();
                    System.out.print("New Name: ");
                    String nameUpdate = scanner.nextLine();
                    System.out.print("New Description: ");
                    String descUpdate = scanner.nextLine();
                    System.out.print("New Location: ");
                    String locUpdate = scanner.nextLine();
                    System.out.print("New Curator ID: ");
                    int curatorUpdate = scanner.nextInt(); scanner.nextLine();
                    System.out.print("New Hours: ");
                    String hourUpdate = scanner.nextLine();

                    Gallery updatedGallery = new Gallery(gid, nameUpdate, descUpdate, locUpdate, curatorUpdate, hourUpdate);
                    boolean updated = galleryService.updateGallery(updatedGallery);
                    System.out.println(updated ? "Gallery updated." : "Update failed.");
                    break;

                case 6:
                    System.out.print("Enter Gallery ID to remove: ");
                    int deleteId = scanner.nextInt(); scanner.nextLine();
                    boolean deleted = galleryService.removeGallery(deleteId);
                    System.out.println(deleted ? "Gallery removed." : "Removal failed.");
                    break;

                case 7:
                    System.out.print("Keyword to find gallery: ");
                    String galleryKey = scanner.nextLine();
                    List<Gallery> galleryList = galleryService.searchGalleries(galleryKey);
                    if (galleryList.isEmpty()) {
                        System.out.println("No galleries matched.");
                    } else {
                        for (Gallery g : galleryList) {
                            System.out.println("---------------");
                            System.out.println("ID: " + g.getGalleryId());
                            System.out.println("Name: " + g.getName());
                            System.out.println("Description: " + g.getDescription());
                            System.out.println("Location: " + g.getLocation());
                            System.out.println("Curator: " + g.getCurator());
                            System.out.println("Open: " + g.getOpeningHours());
                        }
                    }
                    break;

                case 8:
                    System.out.print("User ID: ");
                    int userFavId = scanner.nextInt();
                    System.out.print("Artwork ID: ");
                    int favId = scanner.nextInt(); scanner.nextLine();
                    boolean addedToFav = galleryService.addArtworkToFavorite(userFavId, favId);
                    System.out.println(addedToFav ? "Added to favorites." : "Could not add.");
                    break;

                case 9:
                    System.out.print("User ID: ");
                    int userRemove = scanner.nextInt();
                    System.out.print("Artwork ID: ");
                    int artRemove = scanner.nextInt(); scanner.nextLine();
                    boolean removed = galleryService.removeArtworkFromFavorite(userRemove, artRemove);
                    System.out.println(removed ? "Removed from favorites." : "Remove failed.");
                    break;

                case 10:
                    System.out.print("User ID: ");
                    int userId = scanner.nextInt(); scanner.nextLine();
                    List<Artwork> favList = galleryService.getUserFavoriteArtworks(userId);
                    if (favList.isEmpty()) {
                        System.out.println("No favorites found.");
                    } else {
                        for (Artwork fav : favList) {
                            System.out.println("---------------");
                            System.out.println("ID: " + fav.getArtworkId());
                            System.out.println("Title: " + fav.getTitle());
                            System.out.println("Artist ID: " + fav.getArtistId());
                        }
                    }
                    break;

                case 11:
                    System.out.println("Thank you for visiting the Virtual Art Gallery!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please select between 1 and 11.");
            }
        }
    }
}