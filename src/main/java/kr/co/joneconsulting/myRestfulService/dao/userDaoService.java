package kr.co.joneconsulting.myRestfulService.dao;

import kr.co.joneconsulting.myRestfulService.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


// 서비스로 만들지 않는건 dao 역할도 같이해서
@Component
public class userDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date(),"test1","111-1111"));
        users.add(new User(2, "Alice", new Date(), "test2", "222-2222"));
        users.add(new User(3, "Elena", new Date(),"test3","333-333"));
    }

    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        if (user.getJoinDate() == null){
            user.setJoinDate(new Date());
        }
        users.add(user);

        return user;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User change(User target){
        for(User user : users){
            if(user.getId()==target.getId()){
                user.setName(target.getName());

                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
