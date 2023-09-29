package com.nesum.nesum.user.application.dto;

import static com.nesum.nesum.shared.infrastructure.StaticConstants.ONLY_NUMBER_REGEX;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nesum.nesum.shared.application.dto.GenericEntityDTO;
import com.nesum.nesum.user.domain.Phone;
import com.nesum.nesum.user.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneDTO extends GenericEntityDTO {

	@NotEmpty
    @Size(min = 7, max = 30)
	@Pattern(regexp = ONLY_NUMBER_REGEX )
    String number;

	@NotEmpty
    @Size(min = 1, max = 10)
	@Pattern(regexp = ONLY_NUMBER_REGEX )
    String cityCode;

	@NotEmpty
    @Size(min = 1, max = 10)
	@Pattern(regexp = ONLY_NUMBER_REGEX )
    String countryCode;

    public Phone convertToEntity(User user) {
        var phone = Phone.builder()
                .number(this.number)
                .cityCode(this.cityCode)
                .countryCode(this.countryCode)
                .user(user)
                .build();
        return phone;
    }
}
