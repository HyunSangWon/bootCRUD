package com.example.firstboot.repository;


import com.example.firstboot.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserJpaRespository extends JpaRepository<Users,Long> {
    /*여기서 쿼리를 만들 수 있음

    * findBy {***}  가 Where = {?} 같다고 보면 된다.
    * 뒤에 변수값은 테이블 컬럼이름이 아닌 @Entity 필드변수 이름이랑 같게 해야된다.
    * */
    List<Users> findByteamName(String teamName);

    /* 소속팀이 Sales 이고 연봉이 3천만원 이상인 사람을 조회 하시오*/
    List<Users> findByteamNameAndSalaryGreaterThanEqual(String teamName,int salary);

    /*NativeQuery는 db에 직접 쿼리할 내용이다 컬럼이름이 같아야한다.
    * NativeQuery가 아닌 쿼리는 DTO 클래스 필드변수랑 쿼리가 매핑해야하며 @Param를 통해 값을 넣어준다.
    * */

    /* 각 소속팀 평균 연봉을 구하시오*/
    @Query(value = "SELECT AVG(salary),team_name FROM users GROUP BY team_name",nativeQuery = true)
    List<Object> getAvgSalary();

    /* 소속팀이 Mongo 인원수와 평균연봉을 구하시오 */
    @Query(value = "SELECT AVG(u.salary) FROM Users u WHERE u.teamName = :teamName")
    Integer getAvgSalaryTeam(@Param("teamName") String teamName);


}
