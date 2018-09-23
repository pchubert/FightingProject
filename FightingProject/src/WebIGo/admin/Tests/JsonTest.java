package WebIGo.admin.Tests;


import WebIGo.admin.Bean.User;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.JSONUtils;


public class JsonTest {


    public static void main(String[] args) {
        jsonTx();
    }

    public static void jsonTx() {
        String json = "{\"Uid\":32,\"Uname\":\"hjw\",\"Upwd\":\"1\",\"Uemail\":\"hjw@hjwblog.com\",\"Urealname\"" +
                ":\"环家伟\",\"Udate\":\"2018-9-17 6:33:43\",\"Uphone\":\"142\",\"Utype\":1,\"Ucread\":0,\"Umoney\":23333.0}";

        String jsomStr="{\"Uid\":32,\"Uname\":\"hjw\",\"Upwd\":\"1\",\"Uemail\":\"hjw@hjwblog.com\",\"Urealname\":" +
                "\"环家伟\",\"Udate\":\"Sep 17, 2018 6:33:43 PM\",\"Uphone\":\"142\",\"Utype\":1,\"Ucread\":0,\"Umoney\":23333.0}";
        JsonConfig config = new JsonConfig();
        config.setRootClass(User.class);

       JSONUtils.getMorpherRegistry().registerMorpher( new DateMorpher(new String[] { "yyyy-MM-dd" }));

        PropertyNameProcessor lowerCasePropertyNameProcessor = new PropertyNameProcessor() {
            @Override
            public String processPropertyName(Class aClass, String s) {
                return s.substring(0, 1).toLowerCase() + s.substring(1);
            }
        };

        config.registerJavaPropertyNameProcessor(User.class, lowerCasePropertyNameProcessor);
        //config.registerJavaPropertyNameProcessor(Address.class, lowerCasePropertyNameProcessor);
        JSONObject data = JSONObject.fromObject(jsomStr);

        int status = data.optInt("status");

        System.out.println(status);

        User user = (User) JSONObject.toBean(data, config);
        System.out.println(user.getUname());
        System.out.println(user.getUphone());
        System.out.println(user.getUcread());
        System.out.println(user.getUdate());
        System.out.println(user.getUemail());

    }


    public class Student {
        //姓名
        private String name;
        //年龄
        private String age;
        //住址
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Student [name=" + name + ", age=" + age + ", address="
                    + address + "]";
        }
    }

}
