package Oauth2Web.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {

    Optional<MemberEntity> findByMid(String mid);

}
