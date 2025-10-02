package poly.edu.entity;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
	private String id;
	private String fullname;
	@Default
	private String photo = "photo.jpg";
	@Default
	private boolean gender = true;
	@Default
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date birthday = new Date();
	@Default
	private double salary = 12345.6789;
	@Default
	private Integer level = 0;
}
