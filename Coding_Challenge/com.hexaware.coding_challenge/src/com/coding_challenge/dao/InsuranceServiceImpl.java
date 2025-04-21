package com.coding_challenge.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.coding_challenge.entity.Policy;
import com.coding_challenge.exception.PolicyNotFoundException;
import com.coding_challenge.util.DBConnUtil;

public class InsuranceServiceImpl implements IPolicyService {
    private static Connection conn = DBConnUtil.getConn();

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policies (policy_name, policy_type, premium_amount) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyType());
            ps.setDouble(3, policy.getAmount());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedPolicyId = rs.getInt(1);
                        policy.setPolicyId(generatedPolicyId);
                        return true;
                    }
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Policy with ID " + policy.getPolicyId() + " already exists.");
        } catch (SQLException e) {
            System.err.println("SQL Error while creating policy: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error while creating policy: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String sql = "SELECT * FROM policies WHERE policy_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getString("policy_type"),
                        rs.getDouble("premium_amount")
                );
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PolicyNotFoundException("Database error occurred while fetching policy.");
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String sql = "SELECT * FROM policies";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                policies.add(new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getString("policy_type"),
                        rs.getDouble("premium_amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) throws PolicyNotFoundException {
        getPolicy(policy.getPolicyId());

        String sql = "UPDATE policies SET policy_name = ?, policy_type = ?, premium_amount = ? WHERE policy_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, policy.getPolicyName());
            ps.setString(2, policy.getPolicyType());
            ps.setDouble(3, policy.getAmount());
            ps.setInt(4, policy.getPolicyId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
        getPolicy(policyId);

        String sql = "DELETE FROM policies WHERE policy_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, policyId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
