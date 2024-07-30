package com.zzknu.back_end.domain.friendship.repository;


import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f.to_user FROM Friendship f WHERE f.from_user.id = :userId")
    Page<User> findFollowingByUserId(Long userId, Pageable pageable);

    @Query("SELECT f.from_user FROM Friendship f WHERE f.to_user.id = :userId")
    Page<User> findFollowersByUserId(Long userId, Pageable pageable);
}
