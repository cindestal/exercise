
/*
重点常考！：
final和abstract，private和abstract，static和abstract，这些是不能放在一起的修饰符，
因为abstract修饰的方法是必须在其子类中实现（覆盖），才能以多态方式调用，以上修饰符在修饰方法时期子类都覆盖不了这个方法，
final是不可以覆盖，private是不能够继承到子类，所以也就不能覆盖，
static是可以覆盖的，但是在调用时会调用编译时类型的方法，因为调用的是父类的方法，而父类的方法又是抽象的方法，又不能够调用，所以上的修饰符不能放在一起。
*/


//抽象类，封装了两个行为标准
abstract class GirlFriend{

    abstract void speaking();

    abstract void cooking();

}

class ChinaGirlFriend extends GirlFriend{

    void speaking(){

        System.out.println("你好");

    }

    void cooking(){

        System.out.println("水煮鱼");

    }

}

class AmericanGirlFriend extends GirlFriend{

    void speaking(){

        System.out.println("hello");

    }

    void cooking(){

        System.out.println("roast beef");

    }

}

class Boy{

    GirlFriend friend;

    void setGirlFriend(GirlFriend f){

        friend = f;

    }

    void showGirlFriend(){

        friend.speaking();

        friend.cooking();

    }


    public static void main(String args[]){

        GirlFriend girl =new ChinaGirlFriend(); //这里girl是上转型对象

        Boy boy = new Boy();

        boy.setGirlFriend(girl);

        boy.showGirlFriend();

        girl = new AmericanGirlFriend();  //girl 是上转型对象

        boy.setGirlFriend(girl);

        boy.showGirlFriend();

    }

}
