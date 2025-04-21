package com.coding_challenge.main;

import java.util.*;

import com.coding_challenge.dao.IPolicyService;
import com.coding_challenge.dao.InsuranceServiceImpl;
import com.coding_challenge.entity.Policy;
import com.coding_challenge.exception.PolicyNotFoundException;

public class MainModule {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IPolicyService service = new InsuranceServiceImpl();
		int choice;

		do {
			System.out.println();
			System.out.println("Insurance Management System");
			System.out.println("1. Create Policy");
			System.out.println("2. View Policy by ID");
			System.out.println("3. View All Policies");
			System.out.println("4. Update Policy");
			System.out.println("5. Delete Policy");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid number: ");
				scanner.next();
			}
			choice = scanner.nextInt();

			try {
				switch (choice) {
					case 1:
						scanner.nextLine();

						System.out.print("Enter Policy Name: ");
						String name = scanner.nextLine();

						System.out.print("Enter Policy Type: ");
						String type = scanner.nextLine();

						System.out.print("Enter Policy Amount: ");
						double amount = scanner.nextDouble();

						Policy newPolicy = new Policy(name, type, amount);
						boolean created = service.createPolicy(newPolicy);
						System.out.println(created ? "Policy created successfully." : "Failed to create policy.");
						break;

					case 2:
						System.out.print("Enter Policy ID: ");
						int id = scanner.nextInt();

						Policy found = service.getPolicy(id);
						if (found != null) {
							System.out.println();
							System.out.println("Policy Details:");
							System.out.println("ID     : " + found.getPolicyId());
							System.out.println("Name   : " + found.getPolicyName());
							System.out.println("Type   : " + found.getPolicyType());
							System.out.println("Amount : " + found.getAmount());
						} else {
							System.out.println("Policy not found.");
						}
						break;

					case 3:
						List<Policy> policies = (List<Policy>) service.getAllPolicies();
						if (policies.isEmpty()) {
							System.out.println("No policies available.");
						} else {
							System.out.println();
							System.out.println("All Policies:");
							System.out.printf("%-5s %-25s %-15s %-10s\n", "ID", "Name", "Type", "Amount");
							for (Policy p : policies) {
								System.out.printf("%-5d %-25s %-15s %-10.2f\n",
										p.getPolicyId(), p.getPolicyName(), p.getPolicyType(), p.getAmount());
							}
						}
						break;

					case 4:
						System.out.print("Enter Policy ID to Update: ");
						int updateId = scanner.nextInt();
						scanner.nextLine();

						System.out.print("Enter Updated Policy Name: ");
						String updatedName = scanner.nextLine();

						System.out.print("Enter Updated Policy Type: ");
						String updatedType = scanner.nextLine();

						System.out.print("Enter Updated Policy Amount: ");
						double updatedAmount = scanner.nextDouble();

						Policy updatedPolicy = new Policy(updateId, updatedName, updatedType, updatedAmount);
						boolean updated = service.updatePolicy(updatedPolicy);
						System.out.println(updated ? "Policy updated successfully." : "Failed to update policy.");
						break;

					case 5:
						System.out.print("Enter Policy ID to Delete: ");
						int deleteId = scanner.nextInt();
						boolean deleted = service.deletePolicy(deleteId);
						System.out.println(deleted ? "Policy deleted successfully." : "Policy not found.");
						break;

					case 6:
						System.out.println("Exiting application.");
						break;

					default:
						System.out.println("Invalid choice. Please try again.");
				}

			} catch (PolicyNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter the correct data type.");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("An unexpected error occurred: " + e.getMessage());
				e.printStackTrace();
			}

		} while (choice != 6);

		scanner.close();
	}
}
