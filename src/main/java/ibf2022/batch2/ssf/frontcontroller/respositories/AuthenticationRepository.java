package ibf2022.batch2.ssf.frontcontroller.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {
	@Autowired @Qualifier("frontcontroller")
    private RedisTemplate<String, String> template;
	// TODO Task 5
	// Use this class to implement CRUD operations on Redis


	public void disableUser(String username) {
		
		this.template.opsForValue()
			.set(username, "locked");
	}

	public boolean isLocked(String username) {
		String json = template.opsForValue().get(username);
		if((null == json || json.trim().length() <= 0)){
            return true;
        }

		return false;
	}
}
