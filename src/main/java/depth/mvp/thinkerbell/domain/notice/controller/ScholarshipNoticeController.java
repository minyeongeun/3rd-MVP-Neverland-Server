package depth.mvp.thinkerbell.domain.notice.controller;

import depth.mvp.thinkerbell.domain.notice.dto.EventNoticeDTO;
import depth.mvp.thinkerbell.domain.notice.dto.ScholarshipNoticeDTO;
import depth.mvp.thinkerbell.domain.notice.service.EventNoticeService;
import depth.mvp.thinkerbell.domain.notice.service.ScholarshipNoticeService;
import depth.mvp.thinkerbell.global.dto.ApiResult;
import depth.mvp.thinkerbell.global.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/scholarship")
@RequiredArgsConstructor
public class ScholarshipNoticeController {

    private final ScholarshipNoticeService scholarshipNoticeService;

    @Operation(summary = "명지대 장학/학자금 공지사항 조회", description = "명지대 장학/학자금 공지사항을 조회합니다. (https://www.mju.ac" +
            ".kr/mjukr/259/subview.do)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력 값"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping
    public ApiResult<List<ScholarshipNoticeDTO>> getAllScholarshipNotices(@RequestParam("ssaid") String ssaid) {
        try {
            List<ScholarshipNoticeDTO> notices = scholarshipNoticeService.getAllScholarshipNotices(ssaid);
            return ApiResult.ok(notices);
        } catch (RuntimeException e) {
            return ApiResult.withError(ErrorCode.INTERNAL_SERVER_ERROR, null);
        }
    }
}
