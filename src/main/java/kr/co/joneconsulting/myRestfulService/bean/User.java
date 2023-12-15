package kr.co.joneconsulting.myRestfulService.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@AllArgsConstructor
@JsonIgnoreProperties({"password","ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    @Schema(title = "사용자 ID",description = "사용자 ID는 자동 생성됩니다.")
    private Integer id;

    @Size(min = 2,message = "Name은 2글자 이상 입력해주세요.")
    @Schema(title = "사용자 이름",description = "사용자 이름은 입력받습니다.")
    private String name;

    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다.")
    @Schema(title = "사용자 등록일",description = "사용자 등록일을 입력합니다. 입력이 없을 시 현재 날짜가 지정됩니다.")
    private Date joinDate;

//    @JsonIgnore
    @Schema(title = "사용자 비밀번호",description = "사용자 비밀번호를 입력합니다.")
    private String password;
//    @JsonIgnore
    @Schema(title = "사용자 비밀번호",description = "사용자 주민등록 번호를 입력합니다.")
    private String ssn;
}
