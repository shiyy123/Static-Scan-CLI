import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VulList {

    public static List<Vul> vulList = new ArrayList<>();
    private static Map<String, Integer> map = new HashMap<>();

    /**
     * get the type(id number) of the vulnerability
     * @param vulName the name of vulnerability
     * @return the vul id of the vul list
     */
    public static int getVulType(String vulName){
        if(map.containsKey(vulName)){
            return map.get(vulName);
        }else{
            vulName = vulName.substring(0, vulName.indexOf(','));
            if(map.containsKey(vulName)){
                return map.get(vulName);
            }
        }
        return -1;
    }

    /**
     * Add new item to the vulList and the map
     * @param id vulType
     * @param name vulName
     * @param zh_name zh_vulName
     * @param solution solution
     * @param zh_solution zh_solution
     * @param desc description
     * @param zh_desc zh_description
     */
    private static void addVal(int id, String name, String zh_name, String solution, String zh_solution, String desc, String zh_desc){
        vulList.add(new Vul(id, name, zh_name, solution, zh_solution, desc, zh_desc));
        map.put(name.toLowerCase(), id);
    }

    static {
        int id = 0;
        String name = "Context Leak,Java";
        String zh_name = "JavaContext对象泄露";
        String solution = "";
        String zh_solution = "";
        String desc = "This error type is specific to Android.In Android applications, subtypes of Context (other than Application, which is a special case) are ephemeral components that can be created and destroyed at the discretion of the Android framework.";
        String zh_desc = "这是Android中特有的一种错误类型。在Android应用程序中，Context的子类型（应用程序除外，这是一个特例），是临时的组件，可以在Android框架的判断下创建和销毁。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 1;
        name = "Null Dereference,C";
        zh_name = "C语言空指针引用";
        solution = "";
        zh_solution = "";
        desc = "The program can potentially dereference a null pointer, thereby raising a NullPointerException. Null pointer errors are usually the result of one or more programmer assumptions being violated. Most null pointer issues result in general software reliability problems, but if an attacker can intentionally trigger a null pointer dereference, the attacker might be able to use the resulting exception to bypass security logic or to cause the application to reveal debugging information that will be valuable in planning subsequent attacks.";
        zh_desc = "程序可能会引用引用空指针，从而引发空指针异常。空指针异常通常是一个或多个程序运行结果的假设被违反的结果。大多数空指针问题会导致一般的软件可靠性问题，但是如果攻击者可以故意触发空指针解引用，攻击者可能会使用产生的异常绕过安全逻辑或导致应用程序显示有价值的调试信息计划后续攻击。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 2;
        name = "Null Dereference,ObjectC";
        zh_name = "Objective-C空指针引用";
        solution = "";
        zh_solution = "";
        desc = "The program can potentially dereference a null pointer, thereby raising a NullPointerException. Null pointer errors are usually the result of one or more programmer assumptions being violated. Most null pointer issues result in general software reliability problems, but if an attacker can intentionally trigger a null pointer dereference, the attacker might be able to use the resulting exception to bypass security logic or to cause the application to reveal debugging information that will be valuable in planning subsequent attacks.";
        zh_desc = "程序可能会引用引用空指针，从而引发空指针异常。空指针异常通常是一个或多个程序运行结果的假设被违反的结果。大多数空指针问题会导致一般的软件可靠性问题，但是如果攻击者可以故意触发空指针解引用，攻击者可能会使用产生的异常绕过安全逻辑或导致应用程序显示有价值的调试信息计划后续攻击。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 3;
        name = "Null Dereference,Java";
        zh_name = "Java空指针引用";
        solution = "";
        zh_solution = "";
        desc = "The program can potentially dereference a null pointer, thereby raising a NullPointerException. Null pointer errors are usually the result of one or more programmer assumptions being violated. Most null pointer issues result in general software reliability problems, but if an attacker can intentionally trigger a null pointer dereference, the attacker might be able to use the resulting exception to bypass security logic or to cause the application to reveal debugging information that will be valuable in planning subsequent attacks.";
        zh_desc = "程序可能会引用引用空指针，从而引发空指针异常。空指针异常通常是一个或多个程序运行结果的假设被违反的结果。大多数空指针问题会导致一般的软件可靠性问题，但是如果攻击者可以故意触发空指针解引用，攻击者可能会使用产生的异常绕过安全逻辑或导致应用程序显示有价值的调试信息计划后续攻击。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 4;
        name = "Resource Leak,C";
        zh_name = "C语言资源泄露";
        solution = "";
        zh_solution = "";
        desc = "In computer science, a resource leak is a particular type of resource consumption by a computer program where the program does not release resources it has acquired. This condition is normally the result of a bug in a program. ";
        zh_desc = "在计算机科学中，资源泄漏是计算机程序的特定类型的资源消耗，其中程序不释放其获取的资源。这种情况通常是程序中的错误造成的。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 5;
        name = "Resource Leak,Java";
        zh_name = "Java资源泄露";
        solution = "";
        zh_solution = "";
        desc = "In computer science, a resource leak is a particular type of resource consumption by a computer program where the program does not release resources it has acquired. This condition is normally the result of a bug in a program. ";
        zh_desc = "在计算机科学中，资源泄漏是计算机程序的特定类型的资源消耗，其中程序不释放其获取的资源。这种情况通常是程序中的错误造成的。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 6;
        name = "Unsafe GuardedBy Access,Java";
        zh_name = "Java不安全的存取保护";
        solution = "";
        zh_solution = "";
        desc = "We reports issues when a field or method is accessed when a lock is not held, when the field or method has been annotated with @GuardedBy(lock)";
        zh_desc = "当一个字段或方法被访问时，如果访问者未持有此锁，且该字段或方法已经被@GuardedBy(lock)注解";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 7;
        name = "Memory Leak,C";
        zh_name = "C语言内存泄露";
        solution = "";
        zh_solution = "";
        desc = "This error type is only reported in C and Objective-C code. In Java we do not report memory leaks because it is a garbage collected language.In C, Infer reports memory leaks when objects are created with malloc and not freed.";
        zh_desc = "此类错误类型仅在C和Objective-C中报告。Java中，我们不报告内存泄漏，因为它是一种垃圾收集语言。在C语言中，当一个对象被分配，却没有释放，我们报告内存泄露问题";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 8;
        name = "Memory Leak,ObjectC";
        zh_name = "Objective-C内存泄露";
        solution = "";
        zh_solution = "";
        desc = "This error type is only reported in C and Objective-C code. In Java we do not report memory leaks because it is a garbage collected language. In Objective-C, we reports memory leaks that happen when objects from Core Foundation or Core Graphics don’t get released.";
        zh_desc = "此类错误类型仅在C和Objective-C中报告。Java中，我们不报告内存泄漏，因为它是一种垃圾收集语言。在Objective-C中，当一个来自于基础核心或核心图形库的对象没有释放，我们报告内存泄露问题";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 9;
        name = "Premature nil termination argument,C";
        zh_name = "C语言过早的结束终止符";
        solution = "";
        zh_solution = "";
        desc = "In many variadic methods, nil is used to signify the end of the list of input objects. This is similar to nil-termination of C strings. If one of the arguments that is not the last argument to the method is nil as well, we reports an error because that may lead to unexpected behavior.";
        zh_desc = "在许多变长参数方法中，nil用于表示输入对象列表的末尾。这与C语言字符串的零终止类似。如果其中一个参数不是该方法的最后一个参数也是零，我们会报告错误，因为这可能会导致意外的行为。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 10;
        name = "Premature nil termination argument,ObjectC";
        zh_name = "Objective-C过早的结束终止符";
        solution = "";
        zh_solution = "";
        desc = "In many variadic methods, nil is used to signify the end of the list of input objects. This is similar to nil-termination of C strings. If one of the arguments that is not the last argument to the method is nil as well, we reports an error because that may lead to unexpected behavior.";
        zh_desc = "在许多变长参数方法中，nil用于表示输入对象列表的末尾。这与C语言字符串的零终止类似。如果其中一个参数不是该方法的最后一个参数也是零，我们会报告错误，因为这可能会导致意外的行为。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 11;
        name = "Empty vector access,C++";
        zh_name = "C语言引用空向量";
        solution = "";
        zh_solution = "";
        desc = "The code is trying to access an element of a vector that we believes to be empty. Such an access will cause undefined behavior at runtime.";
        zh_desc = "代码试图访问推断一定为空的向量中的元素。这种访问会导致未定义的行为";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        // not found in the vul list
        id = 12;
        name = "Ivar not null checked,ObjectC";
        zh_name = "Objective-C变量未检测是否为空";
        solution = "";
        zh_solution = "";
        desc = "This error type is only reported in Objective-C. This is similar to Null dereference, but we hasn’t found a whole trace where the error can happen, but only found that a null dereference can happen if an instance variable of a parameter is nil. ";
        zh_desc = "此错误类型仅在Objective-C中报告。这与空指针引用类似，但我们未找到可能发生错误的整个跟踪，只发现如果参数的实例变量为零，则会发生空指针引用。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 13;
        name = "Parameter not null checked,ObjectC";
        zh_name = "Objective-C参数未检测是否为空";
        solution = "";
        zh_solution = "";
        desc = "This error type is reported only in Objective-C. It is similar to Null dereference, but we hasn’t found a whole trace where the error can happen, but only found that a null dereference can happen if you call a method with nil as an argument. Therefore it is only a warning. ";
        zh_desc = "此错误类型仅在Objective-C中报告。它类似于空指针引用，但我们未找到可能发生错误的整个跟踪，只发现如果调用nil作为参数的方法时可能发生空指针引用。因此这只是一个警告。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 14;
        name = "Retain cycle,ObjectC";
        zh_name = "Objective-C保留环";
        solution = "";
        zh_solution = "";
        desc = "A retain cycle is a situation when object A retains object B, and object B retains object A at the same time. ";
        zh_desc = "当对象A保留对象B的同时，对象B也保留对象A，产生了保留环。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 15;
        name = "Retain cycle,ObjectC";
        zh_name = "Objective-C保留环";
        solution = "";
        zh_solution = "";
        desc = "A retain cycle is a situation when object A retains object B, and object B retains object A at the same time. ";
        zh_desc = "当对象A保留对象B的同时，对象B也保留对象A，产生了保留环。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 16;
        name = "abduction case not implemented";
        zh_name = "abduction case未实现";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 17;
        name = "analysis stops";
        zh_name = "分析中止";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 18;
        name = "array of pointsto";
        zh_name = "指向的数组";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 19;
        name = "array out of bounds l1";
        zh_name = "数组越界l1";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 20;
        name = "array out of bounds l2";
        zh_name = "数组越界l2";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 21;
        name = "array out of bounds l3";
        zh_name = "数组越界l3";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 22;
        name = "assert failure";
        zh_name = "断言失败";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 23;
        name = "bad footprint";
        zh_name = "糟糕的footprint";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 24;
        name = "buffer overrun l1";
        zh_name = "缓冲区溢出l1";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 25;
        name = "buffer overrun l2";
        zh_name = "缓冲区溢出l2";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 26;
        name = "buffer overrun l3";
        zh_name = "缓冲区溢出l3";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 27;
        name = "buffer overrun l4";
        zh_name = "缓冲区溢出l4";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 28;
        name = "buffer overrun l5";
        zh_name = "缓冲区溢出l5";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 29;
        name = "buffer overrun s2";
        zh_name = "缓冲区溢出s2";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 30;
        name = "cannot star";
        zh_name = "无法star";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 31;
        name = "checkers access global";
        zh_name = "扫描器全局访问";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 32;
        name = "checkers immutable cast";
        zh_name = "扫描器不可变转换";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 33;
        name = "checkers print c call";
        zh_name = "扫描器打印C语言调用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 34;
        name = "checkers print objc method calls";
        zh_name = "扫描器打印Objective-C调用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 35;
        name = "checkers printf args";
        zh_name = "扫描器打印参数";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 36;
        name = "checkers repeated calls";
        zh_name = "扫描器重复调用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 37;
        name = "checkers trace calls sequence";
        zh_name = "扫描器跟踪调用序列";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 38;
        name = "class cast exception";
        zh_name = "类抛出异常";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 39;
        name = "cluster callback";
        zh_name = "集群回调";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 40;
        name = "codequery";
        zh_name = "代码查询";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 41;
        name = "comparing floats for equality";
        zh_name = "比较浮点数是否相等";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 42;
        name = "condition always false";
        zh_name = "条件判断总是为假";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 43;
        name = "condition always true";
        zh_name = "条件判断总是为真";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 44;
        name = "dangling pointer dereference";
        zh_name = "悬挂指针引用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 45;
        name = "dead store";
        zh_name = "无效的存储";
        solution = "This error is reported in C++. It fires when the value assigned to a variables is never used (e.g., int i = 1; i = 2; return i;).";
        zh_solution = "这个错误是在c++中报告的。当分配给一个变量的值从不使用时，它会触发(例如，int i = 1;i= 2;返回i;)。";
        desc = "这个错误是在c++中报告的。当分配给一个变量的值从不使用时，它会触发(例如，int i = 1;i= 2;返回i;)。";
        zh_desc = "这个错误是在c++中报告的。当分配给一个变量的值从不使用时，它会触发(例如，int i = 1;i= 2;返回i;)。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 46;
        name = "deallocate stack variable";
        zh_name = "释放堆栈变量";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 47;
        name = "deallocate static memory";
        zh_name = "释放静态内存";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 48;
        name = "deallocation mismatch";
        zh_name = "不匹配的取消分配";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 49;
        name = "divide by zero";
        zh_name = "除0";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 50;
        name = "double lock";
        zh_name = "双重锁";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 51;
        name = "eradicate condition redundant";
        zh_name = "清除冗余条件";
        solution = "Condition (x != null) or (x == null) when x cannot be null: the first condition is always true and the second is always false.";
        zh_solution = "当x肯定不为空时，条件(x != null)或(x == null):第一个条件总是正确的，第二个条件总是错误的。";
        desc = "当x肯定不为空时，条件(x != null)或(x == null):第一个条件总是正确的，第二个条件总是错误的。";
        zh_desc = "当x肯定不为空时，条件(x != null)或(x == null):第一个条件总是正确的，第二个条件总是错误的。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 52;
        name = "eradicate condition redundant nonnull";
        zh_name = "清除冗余的非空条件";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 53;
        name = "eradicate field not initialized";
        zh_name = "清除未被初始化的字段";
        solution = "The constructor does not initialize a field f which is not annotated with @Nullable.";
        zh_solution = "构造函数没有初始化一个字段f，如果该字段没有被@Nullable注解。";
        desc = "构造函数没有初始化一个字段f，如果该字段没有被@Nullable注解。";
        zh_desc = "构造函数没有初始化一个字段f，如果该字段没有被@Nullable注解。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 54;
        name = "eradicate field not mutable";
        zh_name = "清除不可变的字段";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 55;
        name = "eradicate field not nullable";
        zh_name = "清除非空的字段";
        solution = "An assignment x.f = v where v could be null and field f is not annotated with @Nullable.";
        zh_solution = "赋值x.f = v，其中v可以为空，而字段f没有用@Nullable注解。";
        desc = "赋值x.f = v，其中v可以为空，而字段f没有用@Nullable注解。";
        zh_desc = "赋值x.f = v，其中v可以为空，而字段f没有用@Nullable注解。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 56;
        name = "eradicate field over annotated";
        zh_name = "在注释上清除字段";
        solution = "This report is inactive by default. Method m is annotated with @Nullable but the method cannot return null.";
        zh_solution = "这个报告默认是非激活状态的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        desc = "这个报告默认是非激活状态的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        zh_desc = "这个报告默认是非激活状态的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 57;
        name = "eradicate field value absent";
        zh_name = "清除缺失的字段值";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 58;
        name = "eradicate inconsistent subclass parameter annotation";
        zh_name = "清除不一致的子类参数注释";
        solution = "A parameter of the overridden method is missing a @Nullable annotation present in the superclass.";
        zh_solution = "覆盖方法的一个参数丢失了父类中的@Nullable注释。";
        desc = "覆盖方法的一个参数丢失了父类中的@Nullable注释。";
        zh_desc = "覆盖方法的一个参数丢失了父类中的@Nullable注释。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 59;
        name = "eradicate inconsistent subclass return annotation";
        zh_name = "消除不一致的子类返回注释";
        solution = "The return type of the overridden method is annotated @Nullable, but the corresponding method in the superclass is not.";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 60;
        name = "eradicate null field access";
        zh_name = "消除空字段访问";
        solution = "A field access of the form x.field where x could be null.";
        zh_solution = "表单中x.field可以访问，但是x可以为空。";
        desc = "表单中x.field可以访问，但是x可以为空。";
        zh_desc = "表单中x.field可以访问，但是x可以为空。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 61;
        name = "eradicate null method call";
        zh_name = "消除空值调用其方法";
        solution = "A method call x.m(…) where x could be null.";
        zh_solution = "方法调用x.m(…)，x可能为空。";
        desc = "方法调用x.m(…)，x可能为空。";
        zh_desc = "方法调用x.m(…)，x可能为空。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 62;
        name = "eradicate parameter not nullable";
        zh_name = "消除参数不可为空";
        solution = "Method call x.m(…, v, …) where v can be null and the corresponding parameter in method m is not annotated with @Nullable";
        zh_solution = "方法调用x.m(…, v, …)，其中v可以为null，且方法m中的相应的参数没有加@Nullable注解";
        desc = "方法调用x.m(…, v, …)，其中v可以为null，且方法m中的相应的参数没有加@Nullable注解";
        zh_desc = "方法调用x.m(…, v, …)，其中v可以为null，且方法m中的相应的参数没有加@Nullable注解";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 63;
        name = "eradicate parameter value absent";
        zh_name = "消除参数值缺失";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 64;
        name = "eradicate return not nullable";
        zh_name = "消除返回值不能为空";
        solution = "Method m can return null, but the method’s return type is not annotated with @Nullable";
        zh_solution = "方法m可以返回null，但是该方法的返回类型没有被@Nullable注释。";
        desc = "方法m可以返回null，但是该方法的返回类型没有被@Nullable注释。";
        zh_desc = "方法m可以返回null，但是该方法的返回类型没有被@Nullable注释。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 65;
        name = "eradicate return over annotated";
        zh_name = "通过注解消除返回值";
        solution = "This report is inactive by default. Method m is annotated with @Nullable but the method cannot return null";
        zh_solution = "这个报告默认是未激活的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        desc = "这个报告默认是未激活的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        zh_desc = "这个报告默认是未激活的。方法m使用@Nullable进行注释，但是该方法不能返回null。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 66;
        name = "eradicate return value not present";
        zh_name = "消除不存在的返回值";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 67;
        name = "eradicate value not present";
        zh_name = "消除不存在的值";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 68;
        name = "failure exe";
        zh_name = "失败的exe";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 69;
        name = "field should be nullable";
        zh_name = "字段应该被设置为可空";
        solution = "This error type is reported in Java. It fires when a field is not marked @Nullable, but it is Nullified in a method, Or tested for null in a method.";
        zh_solution = "这个错误类型是在Java中报告的。它在字段没有标记为@Nullable时触发，但是在方法中可为null，或者在方法中测试为null。";
        desc = "这个错误类型是在Java中报告的。它在字段没有标记为@Nullable时触发，但是在方法中可为null，或者在方法中测试为null。";
        zh_desc = "这个错误类型是在Java中报告的。它在字段没有标记为@Nullable时触发，但是在方法中可为null，或者在方法中测试为null。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 70;
        name = "field not null checked";
        zh_name = "字段非空检查";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 71;
        name = "inherently dangerous function";
        zh_name = "固有的危险函数";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 72;
        name = "interface not thread safe";
        zh_name = "接口不是线程安全的";
        solution = "This error indicates that you have invoked an interface method not annotated with @ThreadSafe from a thread-safe context (e.g., code that uses locks or is marked @ThreadSafe).";
        zh_solution = "这个错误表明您已经调用了一个没有从线程安全上下文(例如使用锁或标记为@ThreadSafe)的@ThreadSafe的接口方法。";
        desc = "这个错误表明您已经调用了一个没有从线程安全上下文(例如使用锁或标记为@ThreadSafe)的@ThreadSafe的接口方法。";
        zh_desc = "这个错误表明您已经调用了一个没有从线程安全上下文(例如使用锁或标记为@ThreadSafe)的@ThreadSafe的接口方法。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 73;
        name = "internal error";
        zh_name = "内部错误";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 74;
        name = "leak after array abstraction";
        zh_name = "数组抽象后泄露";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 75;
        name = "leak in footprint";
        zh_name = "泄露的足迹";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 76;
        name = "missing fld";
        zh_name = "缺失的字段";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 77;
        name = "null test after dereference";
        zh_name = "引用后的null值测试";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 78;
        name = "nullable dereference";
        zh_name = "可空引用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 79;
        name = "pointer size mismatch";
        zh_name = "不匹配的指针大小";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 80;
        name = "precondition not found";
        zh_name = "未找到先决条件";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 81;
        name = "precondition not met";
        zh_name = "未满足先决条件";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 82;
        name = "proc callback";
        zh_name = "进程回调";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 83;
        name = "quandary taint error";
        zh_name = "污染错误";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 84;
        name = "registered observer being deallocated";
        zh_name = "";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 85;
        name = "return expression required";
        zh_name = "需要return表达式";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 86;
        name = "return statement missing";
        zh_name = "return语句缺失";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 87;
        name = "return value ignored";
        zh_name = "返回值被忽略";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 88;
        name = "skip function";
        zh_name = "跳过这个函数";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 89;
        name = "skip pointer dereference";
        zh_name = "跳过空指针引用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 90;
        name = "stack variable address escape";
        zh_name = "堆栈变量地址转义";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 91;
        name = "static initialization order fiasco";
        zh_name = "静态初始化序列失败";
        solution = "This error is reported in C++. It fires when the initialization of a static variable A, accesses a static variable B from another translation unit (usually another .cpp file). There are no guarantees whether B has been already initialized or not at that point.";
        zh_solution = "这个错误是在c++中报告的。它在静态变量A的初始化时触发，从另一个转换单元(通常是另一个.cpp文件)访问静态变量B。没有保证B是否已经被初始化。";
        desc = "这个错误是在c++中报告的。它在静态变量A的初始化时触发，从另一个转换单元(通常是另一个.cpp文件)访问静态变量B。没有保证B是否已经被初始化。";
        zh_desc = "这个错误是在c++中报告的。它在静态变量A的初始化时触发，从另一个转换单元(通常是另一个.cpp文件)访问静态变量B。没有保证B是否已经被初始化。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 92;
        name = "symexec memory error";
        zh_name = "symexec执行内存错误";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 93;
        name = "thread safety violation";
        zh_name = "";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 94;
        name = "unary minus applied to unsigned expression";
        zh_name = "一元负号应用于无符号表达式";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 95;
        name = "uninitialized value";
        zh_name = "未初始化的值";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 96;
        name = "unknown proc";
        zh_name = "未知进程";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 97;
        name = "unreachable code after";
        zh_name = "不可到达的代码";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 98;
        name = "use after free";
        zh_name = "释放后再使用";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 99;
        name = "wrong argument number";
        zh_name = "错误的变量数量";
        solution = "";
        zh_solution = "";
        desc = "";
        zh_desc = "";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

        id = 100;
        name = "Checkers Fragment Retains View";
        zh_name = "检查器Fragment保留视图";
        solution = "";
        zh_solution = "";
        desc = "This error type is Android-specific. It fires when a Fragment type fails to nullify one or more of its declared View fields in onDestroyView. In performance-sensitive applications, a Fragment should initialize all View’s in onCreateView and nullify them in onDestroyView. If a Fragment is placed on the back stack and fails to nullify a View in onDestroyView, it will retain a useless reference to that View that will not be cleaned up until the Fragment is resumed or destroyed.";
        zh_desc = "这个错误类型是android特有的。当一个Fragment类型不能将其已声明的一个或多个视图字段在onDestroyView中废弃时，它就会触发。在性能敏感的应用程序中，Fragment应该初始化onCreateView中的所有视图，并在onDestroyView中取消它们。如果一个Fragment被放置在后栈上，并且不能在onDestroyView中废弃一个视图，它将保留一个无用的引用，该视图将不会被清理，直到该碎片恢复或销毁。";
        addVal(id, name, zh_name, solution, zh_solution, desc, zh_desc);

    }
}
