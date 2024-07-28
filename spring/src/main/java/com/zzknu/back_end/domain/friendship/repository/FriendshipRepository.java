package com.zzknu.back_end.domain.friendship.repository;


import com.zzknu.back_end.domain.friendship.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}
