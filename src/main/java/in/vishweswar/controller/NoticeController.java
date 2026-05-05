package in.vishweswar.controller;

import in.vishweswar.model.Notice;
import in.vishweswar.model.User;
import in.vishweswar.service.NoticeService;
import in.vishweswar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    public NoticeService noticeService;

    @Autowired
    public UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addNotice")
    public Notice addNotice(@RequestBody Notice notice){
//       User user = userService.getUserByEmail(email);
        return noticeService.addNotice(notice);
    }

    @GetMapping("/allNotice")
    public List<Notice> seeNotice(){
        return noticeService.getAllNotice();
    }
}
