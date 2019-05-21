package cn.codeyang.auth.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 4363120992433393828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 50)
	@Column(length = 50)
	private String name;

	@NotNull
	@Size(max = 20)
	@Column(length = 20)
	private String title;

	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String description;
}
