package ynu.mediinsight.MediInsightBackend;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ynu.mediinsight.MediInsightBackend.service.AccountRoleService;
import ynu.mediinsight.MediInsightBackend.service.AccountService;


@SpringBootTest
class MediInsightBackendApplicationTests {

	@Resource
	AccountRoleService accountRoleService;

	@Resource
	AccountService accountService;

	@Test
	void contextLoads() {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

	@Test
	void Test1() {
		accountRoleService.getAllDoctorsID().forEach(System.out::println);
	}

	@Test
	void Test2() {
		accountService.getAllDoctors().forEach(System.out::println);
	}

}
