package vn.hust.hedspi.ezsport.data;

import vn.hust.hedspi.ezsport.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static vn.hust.hedspi.ezsport.data.RandomValue.generateRandomString;

public class UserData implements DataSeeder<User>{
    final private String[] firstnames = {"Phạm","Nguyễn","Lê","Trần","Vũ","Võ","Phan","Trịnh","Đồng","Lý","Phùng","Ngô","Đặng","Đỗ","Hồ","Dương","Đinh","Hoàng","Tống","Huỳnh","Lương","Tô","Cao","Tạ","Ngọ","Đường","Mạc","Đào","Đàm","Doãn","Quách","Tôn","Thiều","Lại","Mã","Đoàn","Tăng","Triệu","Lã","Lữ","Trình"};

    final private String[] lastnames = {"Đồng","Đức","Đăng","Đông","Dũng","Dung","Đạt","Giang","An","Anh","Ánh","Ân","Huy","Huyền","Hà","Hiếu","Hiên","Sơn","Tiên","Tiến","Tâm","Tuyến","Tuyền","Tuyết","Vân","Vũ","Việt","Bình","Biển","Bảo","Văn","Viên","Hương","Huệ","Hiền","Lê","Nguyễn","Nguyên","Nga","Thảo","Thiện","Mai","Thạch","Thái","Doãn","Nam","Sâm","Ngọc","Nghĩa","Linh","Thảo","Thành","Trâm","Chiến","Tiền","Hà","Hoa","Hạ","Bi","Bin","Bống","Phương","Liễu","Liên","Mến","Cầm","Quang","Quyên","Quyền","Quý","Cường","Tùng","Tân","Giáp","Hùng","Kim","Quỳnh"};


    private static String generateEmail(){
        return generateRandomString(5,20)+"@"+generateRandomString(5,10)+"."+generateRandomString(3,6);
    }

    @Override
    public List<User> generate(int amount) {
        Random random = new Random();
        Set<String> roles = Set.of(new String[]{"user"});
        List<User> generateData = new ArrayList<>(List.of());
        for(int i=0;i<amount;i++){
            int randomFirstname = random.nextInt(firstnames.length);
            int randomLastname = random.nextInt(lastnames.length);
            User user = new User();
            user.setFirstname(firstnames[randomFirstname]);
            user.setLastname(lastnames[randomLastname]);
            user.setEmail(generateEmail());
//            user.setRoles(roles);
            generateData.add(user);
        }

        return generateData;
    }
}
