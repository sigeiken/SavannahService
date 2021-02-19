package com.savannahinformatics.SavannahService.repository;

import com.savannahinformatics.SavannahService.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.status =:status")
    List<Message> findMessageByStatus(@Param("status") String status);

    @Modifying
    @Query("UPDATE Message m SET m.status= :status, m.description = :description WHERE m.id = :id" )
    void updateMessageStatusAndDescription(@Param("id") Long id, @Param("status") String status, @Param("description") String description);
}
