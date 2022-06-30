package Oauth2Web.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Integer> { // JpaRepository 상속

    Optional<MemberEntity> findByMid(String mid); // ID 를 이용한 entity 검색

}
