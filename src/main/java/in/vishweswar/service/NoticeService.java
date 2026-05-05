package in.vishweswar.service;

import in.vishweswar.model.Notice;
import in.vishweswar.model.User;
import in.vishweswar.repository.NoticeRepository;
import in.vishweswar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email);
    }

    public Notice addNotice(Notice notice) {

        User user = getCurrentUser();

        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("Only admin can add notice");
        }

        notice.setUser(user);
        return noticeRepository.save(notice);
    }

    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }
}