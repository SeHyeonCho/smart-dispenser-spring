package com.hanium.smartdispenser.history;

import com.hanium.smartdispenser.auth.UserPrincipal;
import com.hanium.smartdispenser.history.dto.HistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/histories")
public class HistoryController {

    private final HistoryQueryFacade historyQueryFacade;

    @GetMapping
    public Page<HistoryResponseDto> getHistory(@AuthenticationPrincipal UserPrincipal user, Pageable pageable) {
        return historyQueryFacade.getHistoriesByUser(user.getUserId(), pageable);
    }
}
