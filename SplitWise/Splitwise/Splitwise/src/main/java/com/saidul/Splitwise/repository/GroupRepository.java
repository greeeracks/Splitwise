package com.saidul.Splitwise.repository;

import com.saidul.Splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    Group findGroupById(int groupId);
}
