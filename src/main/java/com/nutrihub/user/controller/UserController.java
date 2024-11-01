package com.nutrihub.user.controller;


import com.nutrihub.user.dto.UserDto;
import com.nutrihub.user.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/NutriHub/*")
public class UserController {

//    @Autowired
//    private UserServiceImpl userService;
    // @Autowired 지양
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    // 유저 메인 페이지 포워딩
    @RequestMapping("MainPage")
    public String MainPage(){
        return "/user/userMainPage";
    }

    // 유저 로그인 페이지 포워딩
    @RequestMapping("userLoginPage")
    public String LoginPage() { return "/user/userLoginPage";}

    // 유저 회원가입 페이지 포워딩
    @RequestMapping("userJoinPage")
    public String JoinPage() {return "/user/userJoinPage";}


    // 회원가입 기능
    @PostMapping("joinProcess")
    public ResponseEntity<String> JoinProcess(@RequestBody UserDto userDto) {
        try {
            // 사용자 등록 처리
            userService.JoinProcess(userDto);
            // 성공 시 응답
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            // 예외 발생 시, 클라이언트에게 실패 메시지를 전달
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 현재 존재하는 아이디 리스트 얻어오기
    @GetMapping("getIdList")
    public ResponseEntity<List<UserDto>> getIdList(){
        List<UserDto> idList = userService.getIdList();
        return ResponseEntity.ok(idList);
    }

    // 로그인 기능
    @PostMapping("loginProcess")
    public ResponseEntity<String> LoginProcess(@RequestBody UserDto userDto, HttpSession session){

        UserDto authenticatedUser = userService.LoginProcess(userDto);
        if(authenticatedUser != null) {
            session.setAttribute("User", authenticatedUser);

            System.out.println(authenticatedUser);
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }


}
