package com.taihu.secendcar.controller;

import com.taihu.secendcar.entity.*;
import com.taihu.secendcar.service.*;
import com.taihu.secendcar.service.impl.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

/**
 * CarController
 *  二手车系统控制类
 * @author hcj
 * @date 2023-06-25
 */
public class CarSystemController {
    // 创建用户注册、登录对象
    private User loginUser;
    // 创建键盘输入类对象
    private Scanner scan;
    // 创建IUserService对象
    private IUserService userService;
    // 创建ICarService对象
    private ICarService carService;
    // 创建IModelService对象
    private IModelService modelService;
    // 创建IBrandService对象
    private IBrandService brandService;
    // 创建ICollectService对象
    private ICollectService collectService;

    // 创建对比车辆集合对象
    private LinkedList<Car> compareCarList;

    // 生成无参构造方法
    public CarSystemController() {
        // 调用初始化方法
        init();
    }

    /**
     * 初始化对象的方法
     */
    public void init() {
        loginUser = new User();
        scan = new Scanner(System.in);
        userService = new UserServiceImpl();
        carService = new CarServiceImpl();
        modelService = new ModelServiceImpl();
        brandService = new BrandServiceImpl();
        collectService = new CollectServiceImpl();
    }

    /**
     * 运行开启系统看到主菜单的方法
     */
    public void startSystem() {
        System.out.println("=====*****=====欢迎来到二手车交易系统登录注册页面=====*****=====");
        System.out.println("1.注册");
        System.out.println("2.登录");
        System.out.println("3.退出系统");
        System.out.println("请选择：");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                // 1.注册
                register();
                break;
            case 2:
                // 2.登录
                login();
                break;
            case 3:
                // 3.退出系统
                System.out.println("谢谢使用!");
                System.exit(0); // jvm退出
                break;
            default:
                System.out.println("请遵守规则，输入数字1-3!");
                break;
        }
    }

    /**
     * 1.注册
     */
    public void register() {
        System.out.println("-----------欢迎来到注册页面------------");
        System.out.println("请输入注册的用户名:");
        String username = scan.next();
        // 先去验证此输入的用户名是否被占用,调用service的方法
        User user = userService.findUserByUsername(username);
        // 判断user是否为空
        if (user != null) {
            System.out.println("该用户" + username + "已被占用，请重新输入");
            // 递归
            register();
        } else {
            // user为空，说明username没有被占用，可以注册(向表user中插入数据)
            System.out.println("请输入注册的密码:");
            String password = scan.next();
            System.out.println("请确认密码:");
            String repassword = scan.next();
            if (password.equals(repassword)) {
                // 说明两次密码输入一致
                System.out.println("请先开个户,冲一笔钱进入账户:");
                double money = scan.nextDouble();
                // 创建一个注册对象
                User registerUser = new User();
                // 赋值
                registerUser.setId(UUID.randomUUID().toString());
                registerUser.setUsername(username);
                registerUser.setPassword(password);
                registerUser.setBalance(money);
                // 向user表中新增一条数据
                userService.addUser(registerUser);
                // 成功提示
                System.out.println("⭐⭐⭐👍👍👍用户注册成功！3秒后跳转到登录注册页面......");
                try {
                    Thread.sleep(3000); // 1000毫秒=1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startSystem();
            } else {
                System.out.println("两次密码输入不一致！");
                // 递归
                register();
            }
        }

    }

    /**
     * 2.登录
     */
    public void login() {
        System.out.println("-----------欢迎来到登录页面------------");
        System.out.println("请输入登录的用户名:");
        String username = scan.next();
        // 首先要判断你输入的用户名是否存在，存入才能登录
        User user = userService.findUserByUsername(username);
        if (user != null) {
            // 定义一个标记(开关思想)
            boolean flag = false;
            // 说明此用户存在，存在后才能继续输入对应的密码
            while (!flag) {
                System.out.println("请输入登录的密码:");
                String password = scan.next();
                if (user.getPassword().equals(password)) {
                    loginUser = user;
                    flag = true; // 密码验证正确就做个标记(说明教室有人，就开灯)
                    // 密码输入正确，提示登录成功
                    System.out.println("👍👍👍登录成功！3秒后跳转到主菜单页面......");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 进入主菜单页面
                    mainMenu();
                } else {
                    System.out.println("你输入的密码不正确，请重新输入！");
                }
            }
        } else {
            // user为空说明此用户不存在，提示先去注册
            System.out.println("该用户" + username + "不存在，请先去注册！1秒后自动跳转到注册页面...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 调用注册方法
            register();
        }
    }

    /**
     * 二手车系统主页面
     */
    public void mainMenu() {
        System.out.println("=====*****=====欢迎来到二手车交易系统主菜单页面=====*****=====");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.对比车辆");
        System.out.println("4.我的收藏");
        System.out.println("5.退出系统");
        System.out.println("请选择：");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                // 1.最新二手车信息
                newCarInfo();
                break;
            case 2:
                // 2.搜索车辆
                searchCar();
                break;
            case 3:
                // 3.对比车辆
                myCompares();
                break;
            case 4:
                // 4.我的收藏
                myCollects();
                break;
            case 5:
                // 5.退出系统
                System.out.println("谢谢使用!");
                System.exit(0); // jvm退出
            default:
                System.out.println("请遵守规则，输入数字1-5!");
                break;
        }
    }

    /**
     * 1.最新二手车信息
     */
    public void newCarInfo() {
        // 调用service层
        List<Car> list = carService.findAll();
        // 调用在控制台打印输出所有车辆信息(前10辆)的方法
        printCarListInfo(list);
        // 根据车前面的序列号选择单车辆详情
        System.out.println("请选择序号：");
        int selectNumber = scan.nextInt();
        Car car = list.get(selectNumber - 1);
        // 调用打印一辆车的详细信息方法
        printOneCarInfo(car);
        // 收藏、加入对比和购买的菜单项
        collectOrCompareOrBuyMenu(car);
    }

    /**
     * 在控制台打印输出所有车辆信息(前10辆)
     */
    public void printCarListInfo(List<Car> list) {
        // 先判断集合不为空
        if (list.isEmpty()) {
            System.out.println("目前，没有车辆相关信息，请先去添加车信息！");
        } else {
            System.out.println("品牌   车型    里程数   价格  发布时间");
            // 遍历集合
            for (int i = 0; i < list.size(); i++) {
                Car c = list.get(i);
                // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
                Model m = modelService.findModelById(c.getModelId());
                // 根据m.getBrandId()到品牌brand表查询品牌名称
                Brand b = brandService.findBrandById(m.getBrandId());
                System.out.println((i + 1) + "." + b.getBrandName() + "\t" + m.getModelName() + "\t" +
                        c.getMileage() + "公里\t" + c.getPrice() + "元\t" + c.getPublishDate());
            }
        }
    }

    /**
     * 在控制台打印输出一车辆详细信息
     */
    public void printOneCarInfo(Car car) {
        if (car != null) {
            System.out.println("品牌   车型    里程数   价格  发布时间   排量  上牌时间  离合器类型");
            // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
            Model m = modelService.findModelById(car.getModelId());
            // 根据m.getBrandId()到品牌brand表查询品牌名称
            Brand b = brandService.findBrandById(m.getBrandId());
            System.out.println(b.getBrandName()+"\t"+m.getModelName() + "\t" +
                    car.getMileage() + "公里\t"+car.getPrice()+"元\t"+car.getPublishDate()+"\t"+
                    car.getDisplacement() +"\t"+car.getPassDate()+"\t"+car.getClutchTypeStr());
        }
    }
    /**
     * 收藏、加入对比和购买的菜单项
    */
    public void collectOrCompareOrBuyMenu(Car car){
        System.out.println("1.收藏");
        System.out.println("2.加入对比");
        System.out.println("3.购买");
        System.out.println("4.返回主菜单");
        System.out.println("请选择：");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                // 1.收藏
                joinCollectCar(car);
                break;
            case 2:
                // 2.加入对比
                joinCompareCar(car);
                // 如果对比车辆未满4辆，可以继续加入对此
                while(true){
                    // 调用继续加入对比的方法
                    continueJoinCompareCar(car);
                    // 对比集合满4辆车，就不能继续加入对比
                    if(compareCarList.size()==4){
                        System.out.println("对比车辆已满4辆，不能再添加！！！");
                        break; // 终止当前while循环
                    }
                }
                // 后面可以打印车辆对比信息
                printCompareCarInfo();
                break;
            case 3:
                // 3.购买
                buyCar(car);
                break;
            case 4:
                // 4.返回主菜单
                mainMenu();
                break;
            default:
                System.out.println("请遵守规则，输入数字1-4!");
                break;
        }
    }
    /**
     * 3.购买(只能在登陆后才能购买)
    */
    public void buyCar(Car car){
        // 先判断是否登录
        if(loginUser!=null){
            // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
            Model m = modelService.findModelById(car.getModelId());
            // 根据m.getBrandId()到品牌brand表查询品牌名称
            Brand b = brandService.findBrandById(m.getBrandId());
            System.out.println("你确定要购买："+m.getModelName()+"这辆车吗?");
            System.out.println("请输入你的选择(y:代表确定购买，其他任意键代表不买):");
            String yes = scan.next();
            if(yes.equals("y")){ // 自己输入的就是y
                // 判断自己的余额跟此辆车的价格
                if(loginUser.getBalance()>=car.getPrice()){ // 余额够
                    // 获取车的价格
                    double carPrice = car.getPrice();
                    // 获取自己的余额
                    double myBalance = loginUser.getBalance();
                    // 购买好后  余额-车价格
                    myBalance = myBalance-carPrice;
                    // 再把余额存入自己的账户中
                    loginUser.setBalance(myBalance);
                    // 更新自己账户余额
                    userService.updateUserBalance(loginUser);
                    // 成功卖掉的车要下架
                    carService.updateCar(car);
                    System.out.println("恭喜你购买"+m.getModelName()+"成功，您支付了"+carPrice+"元，账户余额为："+myBalance+"元");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 购买成功后跳转到主菜单
                    mainMenu();
                }else{ // 余额不够
                    System.out.println("你个穷鬼，好好去搬砖挣钱吧.......开始挣钱中.....");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 搬砖挣好钱后，往余额中存钱
                    System.out.println("搬砖挣了：");
                    double money = scan.nextDouble();
                    loginUser.setBalance(money+loginUser.getBalance());
                    userService.updateUserBalance(loginUser);
                    // 递归 回头再去购买
                    buyCar(car);
                }
            }
        }
    }

    /**
     * 搜索车辆
    */
    public void searchCar(){
        System.out.println("1.根据品牌搜索");
        System.out.println("2.根据价格搜索");
        System.out.println("3.根据上牌日期搜索");
        System.out.println("请选择：");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                // 1.根据品牌搜索
                searchByBrand();
                break;
            case 2:
                // 2.根据价格搜索
                break;
            case 3:
                // 3.根据上牌日期搜索
                break;
            default:
                System.out.println("请遵守规则，输入数字1-3!");
                break;
        }
    }
    /**
     * 根据品牌搜索
    */
    public void searchByBrand(){
        // 首先要查找所有的品牌集合
        List<Brand> brandList = brandService.findBrandAll();
        // 遍历品牌集合
        for (int i = 0; i < brandList.size(); i++) {
            Brand b = brandList.get(i);
            System.out.println((i+1)+"."+b.getBrandName());
        }
        System.out.println("请品牌（序号）：");
        int choiceBrandQueue = scan.nextInt();
        // 根据brandId到model表中查询Model对象集合
        List<Model> modelList = modelService.findModelByBrandId(brandList.get(choiceBrandQueue-1).getId());
        // 遍历车型集合
        for (int i = 0; i < modelList.size(); i++) {
            Model m = modelList.get(i);
            System.out.println((i+1)+"."+m.getModelName());
        }
        // 根据序号查询车辆的详情信息
        System.out.println("请车型（序号）：");
        int choiceModel = scan.nextInt();
        // 根据你选择的车型序号查询出对应车型对象
        Model model = modelList.get(choiceModel-1);
        // 根据车型id查询出车辆对象
        List<Car> carList = carService.findCarByModelId(model.getId());
        // 调用在控制台打印输出所有车辆信息
        printCarListInfo(carList);
        // 根据车前面的序列号选择单车辆详情
        System.out.println("请选择序号：");
        int selectNumber = scan.nextInt();
        Car car = carList.get(selectNumber - 1);
        // 调用打印一辆车的详细信息方法
        printOneCarInfo(car);
        // 收藏加入对比或构造
        collectOrCompareOrBuyMenu(car);
    }

    /**
     * 1.加入收藏
    */
    public void joinCollectCar(Car car){
        if(loginUser!=null){
            // 判断当前用户下的所有收藏车辆
            LinkedList<Collect> collectList = collectService.findCollectCarByUserId(loginUser.getId());
            // 判断该车辆是否是重复收藏过
            int index = -1;
            for (int i = 0; i < collectList.size(); i++) {
                // 判断集合中是否有该辆车
                if(collectList.get(i).getCarId().equals(car.getId())){
                    // 说明收藏过
                    index = i;
                    break; // 终止当前循环
                }
            }
            if(index!=-1){ // 说明收藏过
                System.out.println("对不起！该辆车已经收藏过！！！");
                // TODO 更新该车的收藏时间

            }else{ // 说明未收藏过
                // 创建Collect对象
                Collect col = new Collect();
                col.setId(UUID.randomUUID().toString());
                col.setCarId(car.getId());
                col.setUserId(loginUser.getId());
                col.setCreateDate(new Date());
                // 个人收藏列表最多只能收藏10辆车
                if(collectList.size()>=10){
                    // 满10辆车就移出第一辆收藏的车后
                    collectList.removeFirst();
                    // 再加入此辆车收藏
                    collectList.add(col);
                }else{
                    // 收藏列表中不满10辆车，就直接加入
                    collectList.add(col);
                }
                // 调用service层，添加收藏
                collectService.addCollect(col);
                System.out.println(car.getId()+"收藏成功！");
                // 收藏成功后回到主菜单
                mainMenu();
            }
        }
    }
    /**
     * 4.我的收藏
    */
    public void myCollects(){
        if(loginUser!=null){
            // 根据此用户的userId查询该用户下所有的收藏列表
            LinkedList<Collect> collects = collectService.findCollectCarByUserId(loginUser.getId());
            // 打印输出我的收藏列表
            printMyCollectsCarInfo(collects);
            System.out.println("是否要回到上一级：(y)");
            String yes = scan.next();
            if(yes.equals("y")){
                // 返回上一级
                mainMenu();
            }else{
                // 其他任意键就退出
                System.out.println("欢迎下次使用，再见！");
                System.exit(0);
            }
        }
    }
    /**
     * 在控制台打印输出我的收藏列表车辆信息
    */
    public void printMyCollectsCarInfo(List<Collect> list){
        if(list.isEmpty()){
            System.out.println("当前登录用户没有收藏车辆！请先去收藏");
            mainMenu();
        }else{
            System.out.println("品牌   车型    里程数   价格  发布时间   排量  上牌时间  离合器类型");
            // 遍历收藏列表list
            for (int i = 0; i < list.size(); i++) {
                Collect col = list.get(i);
                // 根据carId查询车辆信息
                Car car = carService.findCollectCarByCarId(col.getCarId());
                if(car!=null){
                    // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
                    Model m = modelService.findModelById(car.getModelId());
                    // 根据m.getBrandId()到品牌brand表查询品牌名称
                    Brand b = brandService.findBrandById(m.getBrandId());
                    System.out.println((i+1)+"."+b.getBrandName()+"\t"+m.getModelName() + "\t" +
                            car.getMileage() + "公里\t"+car.getPrice()+"元\t"+car.getPublishDate()+"\t"+
                            car.getDisplacement() +"\t"+car.getPassDate()+"\t"+car.getClutchTypeStr());
                }
            }
        }
    }

    /**
     * 2.加入对比
    */
    public void joinCompareCar(Car car){
        if(loginUser!=null){
            // 把对比车辆放在当前用户的一个集合中
            compareCarList = loginUser.getCompareCarList();
            // 判断是否重复添加对比车辆
            if(compareCarList.contains(car)){  // 加入过对比
                System.out.println("该辆车已经加入过对比车辆集合中......");
                collectOrCompareOrBuyMenu(car);
            }else{ // 没有加入过对比
                // 对比列表最多只能存储4辆车的信息
                if(compareCarList.size()>5){
                    // 如果对比车辆已满，则给出提示信息，
                    System.out.println("对比车辆已满");
                    // 同时可以选择是否要将页面跳转至"对比车辆"菜单项
                    System.out.println("你是否要将页面跳转至'对比车辆'菜单项");
                    String yes = scan.next();
                    if(yes.equals("y")){
                        // 跳转至'对比车辆'菜单项
                        myCompares();
                    }else{
                        // 输入其他键，跳转至加入对比、购买菜单中
                        collectOrCompareOrBuyMenu(car);
                    }
                }else{
                    // 讲该辆车添加到对比集合中
                    compareCarList.add(car);
                    System.out.println("添加对比车辆成功!");
                }
            }
        }
    }
    /**
     * 是否继续添加对比车辆(输入y继续添加，其他任意键到主菜单)
     */
    public void continueJoinCompareCar(Car car){
        System.out.println("是否继续添加对比车辆,输入y(y:继续，其他任意键到主菜单)");
        String yes = scan.next();
        if(yes.equalsIgnoreCase("y")){
            // 调用查询所有车辆
            List<Car> carList = carService.findAll();
            // 调用打印所有车辆的方法
            printCarListInfo(carList);
            System.out.println("请选择(序号):");
            int choice= scan.nextInt();
            // 调用加入对比的方法
            joinCompareCar(carList.get(choice-1));
        }else{
            // 其他任意键到主菜单
            mainMenu();
        }
    }
    /**
     * 打印车辆对比信息
    */
    public void printCompareCarInfo(){
        System.out.println("该车辆已加入对比列表，按0查看对比车辆信息，其它键返回主菜单：");
        int choice = scan.nextInt();
        if(choice==0){
            // 调用对比车辆方法
            myCompares();
        }else{
            // 其它键返回主菜单
            mainMenu();
        }
    }

    /**
     * 3.对比车辆
    */
    public void myCompares(){
        if(loginUser!=null){
            // 获取对比车辆列表集合
            List<Car> list = loginUser.getCompareCarList();
            // 先判断对比车辆集合至少有2辆车，才好进行对比
            if(list.size()<=1){
                System.out.println("请先去添加对比2辆车以上");
                mainMenu();
            }
            // 如果if不走，说明2辆车及以上
            // 创建集合，用于封装所有车辆的对比信息
            ArrayList<CompareInfo> comparInfoList = new ArrayList();
            // 遍历5次
            for (int i = 1; i <= 5; i++) {
                // 创建对比车辆对象
                CompareInfo compareInfo = new CompareInfo();
                if(i==1){
                    compareInfo.getTitle().add("xxx品牌：");
                    for (Car car : list) {
                        // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
                        Model m = modelService.findModelById(car.getModelId());
                        // 根据m.getBrandId()到品牌brand表查询品牌名称
                        Brand b = brandService.findBrandById(m.getBrandId());
                        // 把品牌名称加入title中
                        compareInfo.getTitle().add(b.getBrandName());
                    }
                }else if(i==2){
                    compareInfo.getTitle().add("xxx车型：");
                    for (Car car : list) {
                        // 根据car.getModelId()到车型model表查询对应的车型名称和品牌id
                        Model m = modelService.findModelById(car.getModelId());
                        // 把车型名称加入title中
                        compareInfo.getTitle().add(m.getModelName());
                    }
                }else if(i==3){
                    compareInfo.getTitle().add("xxx价格：");
                    for (Car car : list) {
                        // 把车辆价格加入title中
                        compareInfo.getTitle().add(car.getPrice());
                    }
                }else if(i==4){
                    compareInfo.getTitle().add("xxx排量：");
                    for (Car car : list) {
                        // 把排量加入title中
                        compareInfo.getTitle().add(car.getDisplacement());
                    }
                }else if(i==5){
                    compareInfo.getTitle().add("xxx里程数：");
                    for (Car car : list) {
                        // 把里程数加入title中
                        compareInfo.getTitle().add(car.getMileage());
                    }
                }
                // 将每个对比对象compareInfo添加对比集合中
                comparInfoList.add(compareInfo);
            }
            // 遍历对比车辆信息的集合
            for (CompareInfo cInfo : comparInfoList) {
                for (Object obj : cInfo.getTitle()) {
                    System.out.print(obj+"\t\t");
                }
                System.out.println();
            }
        }
    }
}

