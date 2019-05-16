package com.example.firstboot.controller;

import com.example.firstboot.exception.ResourceNotFoundException;
import com.example.firstboot.model.Users;
import com.example.firstboot.repository.UserJpaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class MyController {



    @Autowired
    private UserJpaRespository userJpaRespository;

    /*All select*/
    @GetMapping(value="/all")
    public List<Users> findAll(){
       return userJpaRespository.findAll();
}
    /*select
    * param name까지 컬럼이름과 동일해야 한다.
    *
    * */
    @GetMapping("/find/{teamName}")
    public List<Users> findByName(@PathVariable final String teamName){
        return userJpaRespository.findByteamName(teamName);
    }

    @GetMapping("/find/{teamName}/{salary}")
    public List<Users> findByteamNameAndSalaryGreaterThanEqual(@PathVariable final String teamName,
                                                               @PathVariable final int salary){
        return userJpaRespository.findByteamNameAndSalaryGreaterThanEqual(teamName, salary);
    }

    /*group by avg salary nativeQuery*/
    @GetMapping("/avg/salary")
    public List<Object> getAvgSalary(){
        return userJpaRespository.getAvgSalary();
    }

    /*group by avg salary none nativeQuery*/
    @GetMapping("/salary/{teamName}")
    public Integer getAvgSalaryTeam(@PathVariable final String teamName){
        return userJpaRespository.getAvgSalaryTeam(teamName);
    }

    /*insert*/
    @PostMapping(value="/load")
    public List<Users> load(@RequestBody final Users users){
         userJpaRespository.save(users);
        return userJpaRespository.findAll();
    }

    /*update*/
    @PutMapping(value="/{id}")
    public List<Users> updateUsers(@PathVariable final Long id,
                                   @Valid@RequestBody Users users){

        userJpaRespository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Users","id",id));


        users.setSalary(users.getSalary());
        users.setTeamName(users.getTeamName());

        Users upt_users = userJpaRespository.save(users);

        return userJpaRespository.findAll();
    }

    /*delete*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Users user = userJpaRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userJpaRespository.delete(user);

        return ResponseEntity.ok().build();
    }



}
