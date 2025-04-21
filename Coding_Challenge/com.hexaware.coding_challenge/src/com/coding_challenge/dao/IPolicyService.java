package com.coding_challenge.dao;

import com.coding_challenge.entity.Policy;
import com.coding_challenge.exception.PolicyNotFoundException;

import java.util.Collection;

public interface IPolicyService {
    boolean createPolicy(Policy policy);

    Policy getPolicy(int policyId) throws PolicyNotFoundException;

    Collection<Policy> getAllPolicies();

    boolean updatePolicy(Policy policy) throws PolicyNotFoundException;

    boolean deletePolicy(int policyId) throws PolicyNotFoundException;
}
