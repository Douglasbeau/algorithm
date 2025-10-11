

**设计模式**（可复用面向对象的基础） 1-24 Jul 6<br>
1. 设计模式是设计经验的沉淀，其作用是使设计有针对性、通用性，并且对未来的需求和问题有复用性。
2. 设计模式有四个基本要素：模式名、问题、解决方案（对象组成及其职责）和解决效果。
3. 设计模式按照目的可以分3类：创建型、结构型、行为型。按照范围可以分为2类：类、对象。  
   比如，*创建型类模式*将对象的部分创建工作延迟到子类。*创建型对象模式*将它延迟到另一个对象中。  
   *行为型类模式*使用继承描述算法和控制流，*行为型对象模式*描述多对象如何协作完成单对象不能完成的任务。
4. 利用设计模式解决问题的过程：
    1. 寻找合适的对象——它们应该是系统分解出来的对象集合，需要考虑封装、粒度、依赖关系、灵活性等。
    2. 决定对象的粒度。
    3. 指定对象接口——每一个操作的名称、参数和返回值构成signature，其组合就是接口。
    4. 描述对象的实现——即怎么定义一个对象或类，包含哪些数据，能做什么操作。
    5. 运用复用机制——如何使软件灵活和可复用。组合优于继承，参数化类型是面向对象系统中除了类继承和  
       对象组合之外的组合行为的方式。
    6. 关联运行时和编译时的结构。
    7. 设计应该支持变化——考虑易变部分，将其封装。
5. 选择设计模式时，要考虑它们怎么解决问题，关注其意图，模式之间的关联，封装变化。

**设计模式**（可复用面向对象的基础） 25-36 Jul 7<br>
*实例：设计一个"所见即所得"的文档编辑器*
1. 编辑器编辑的文档应该按怎样的结构组织起来？首先文档是字符、线、图形元素的集合，可以被物理地划分为  
   行、列、图形、表和其他子结构，子结构也可能有子结构。我们可以一致地对待文本、图形和元素、元素组，  
   故可以选择**递归模式**——基本元素组成组合（行、列），后者也可以组成更大的组合。
2. 然后，文档结构中所有元素可以抽象出**图元**，包括元素和组合。图元的职责包括画出自己、边界、点击检测，  
   插入、删除等，并且有指向父、子对象的指针。
3. 文档物理结构有了，怎么展示出来呢？为了支持不同格式化的算法，我们可以将其封装到类Compositor中，  
   被算法格式化的图元是Composition的特定图元的各个子图元。Composition和Compositor的分离保证了将  
   支持文档物理结构的代码和支持不同格式化算法的代码相互分离。这其实就是**策略模式**，前者是上下文。
4. 那如何修饰用户界面呢（比如添加滚动条或者边界）？考虑修饰用户界面要修改代码，我们可以采用继承的  
   方式来扩充——问题是导致类爆炸。还是采用组合吧，可是图元和边界，谁来组合谁呢？无论有没有边界，  
   客户应一致地对待图元。另外，边界也是有形的，就也作为图元的子类，可以定义为MonoGlyph，它有draw()
   方法会额外画出边界（根据具体实现），这就是**装饰模式**。

**设计模式**（可复用面向对象的基础） 37-50 Jul 9<br>
*实例：设计一个"所见即所得"的文档编辑器*
1. 考虑编辑器的可移植性，怎么让它支持多视感标准呢？比如Glyph的一个子类ScrollBar，它在不同平台可以有  
   诸如MotifScrollBar、PMScrollBar和MacScrollBar等实现。再如Button也会有对应的多种实现。首先，在代码  
   里直接用这些实现类创建对象是不好的，因为只能支持一个系统。然后，需要确定创建合适的窗口组件所需的   
   视感标准，可以整体地替换不同类型的组件，**抽象工厂模式**就可以解决这个问题。抽象工厂GUIFactory有不同子类  
   对应于不同视感标准的实现，包括createScrollBar、createButton创建组件的方法。每种组件（ScrollBar、  
   Button等）也分别有其不同标准的实现。只要确定了GUIFactory的类型（可以通过配置在运行时确定），就能  
   整个*系列*地创建所需组件。
2. 一个类似的但更复杂的移植问题是，如何支持多种窗口系统？比如，互不相容的Windows、Macintosh、X等。
   这与视感独立性的最大区别是，窗口系统的类层次很不兼容，没法给每个组件都创建公共抽象产品类。更具体  
   地，考虑显示图元的窗口Window类，它会有drawLine、drawRect、redraw、raise、lower、iconify等方法，  
   要让它跨系统，两个极端观点：一、实现功能的交集，缺点是无法支持高级功能（即使多数系统有其实现）；  
   二、实现功能的并集，缺点是规模会极大且厂商对窗口系统的修改会迫使我们修改接口。现在折中一下，只实  
   现多数系统支持的功能，缺点是也会导致很多是否支持某功能的考虑，且很难维护，也没有使用的灵活性。  
   **桥接模式**就可以解决这些痛点——用抽象类WindowImp作为Window的实现，其子类如WindowsWindowImp、  
   MacWindowImp、XWindowImp等封装系统相关实现，Window的子类如AppWindow、DialogWindow等只关心  
   应用级别的实现，引用WindowImp进行具体操作。这样就允许分离的类层次一起工作，完美解决了上述问题。
3. 用户对文档执行增、删、修改字体、保存等操作时，可能通过菜单，也可能通过键盘，这些不同的用户接口都  
   应能达到目的。除此之外，还应该支持undo、redo的功能，但不是所有操作都支持。操作是五花八门的，显然  
   已经渗透到应用中了，我们怎么实现它还能保证简单、可扩充呢？可以用**命令模式**，为了对不同用户接口相同  
   操作请求做出相同的响应，我们可以封装请求为对象，这个对象可以保存状态，以支持redo/undo。Command  
   接口包括execute、unexecute和reversible方法，reversible用来返回一中Command是否支持撤销。最后一个  
   问题，如何支持redo/undo？我们可以将命令历史记录放到一个list中，按先后顺序放已完成的操作，一个指针  
   指向最后完成的操作，其前面是可以被undo的，后面是可以被redo的命令。


**设计模式**（可复用面向对象的基础） 50-61 Jul 10<br>
*实例：设计一个"所见即所得"的文档编辑器*
1. 文档编辑器还需要拼写检查和断字处理功能，为了将功能与文档结构解耦，我们需要考虑两个事情：一、访问  
   需要分析的信息，即分散在文档结构中的图元；二、分析这些信息。
2. 访问分散信息：例如要访问文本以进行拼写检查，文本是分散在图元对象的层次结构中的。访问机制得能应对  
   不同的数据结构、访问顺序（如先序、中序、后序等）。一个笨方法是在Glyph接口加入next、isDone等方法  
   来支持遍历，缺点是，面对访问需求的变化（如先序遍历时跳过非文本图元），必须更改已有代码。好的办法  
   **迭代器模式**，将上述方法封装到Iterator中，Glyph接口增加createIterator方法来获取相应的Iterator。该模式的  
   好处是对客户隐藏了被遍历的对象的内部结构。
3. 分析信息：为了能让分析的种类随意扩充，我们可以将分析方法封装到Visitor，在Glyph增加accept(Visitor v)  
   方法，以进行不同分析——Visitor接口有visitChar、visitRow、visitImage等方法。这就是**访问者模式**，可以很  
   方便地扩充分析方法。缺点是每增加一个图元。都需要修改Visitor增加对应方法，好在这种情况不常见。
4. 总结一下，在文档编辑器设计过程中，用到了以下8中设计模式：
    1. 组合，表示文档的物理结构。
    2. 策略，允许不同的格式化算法。
    3. 装饰器，修饰用户界面。
    4. 抽象工厂，支持多视感标准。
    5. 桥接，允许多个窗口平台。
    6. 命令，支持撤销用户操作。
    7. 迭代器，访问和遍历对象的结构。
    8. 访问者，方便扩充分析能力的同时避免文档结构的实现复杂化。


**设计模式**（可复用面向对象的基础） 62-81 Jul 11<br>
*Abstract Factory和Builder模式*
1. Abstract Factory模式的意图是，提供一个接口，以创建一系列相关或相互依赖的对象，而无须指定具体的类。
  参与的类有AbstractFactory、ConcreteFactory、AbstractProduct、ConcreteProduct。客户使用具体的工厂来  
  创建相应的产品对象（系列）。AbstractFactory包含createProductA、createProductB、……方法，具体工厂  
  类实现所有这些方法，产品对象的创建延迟到具体工厂类中。  
  客户接收AbstractFactory对象作为参数，这使得客户与类的实现分离，方便整个系列产品的创建或替换。但若  
  增加ProductC，则AbstractFactory及其所有子类都得修改——增加createProduct方法。
2. Builder模式的意图是，将一个复杂对象的构建过程及其表示相分离，使得同样的构建过程能得到不同的表示。  
  两个关键类是Builder和Director，Builder指定创建Product各个部件的抽象接口，Director构造一个使用Builder  
  接口的对象。ConcreteBuilder是Builder的子类，实现了其接口以装配部件，还定义、跟踪相关表示。Product  
  的子类在相应的ConcreteBuilder中被定义内部表示和装配过程。Builder中可以有各种buildXXX方法，和一个  
  必备的getResult方法，用来返回目标对象。  
  Builder模式使得我们可以通过选择具体的Builder给Director来构造想要的对象，具体实现被Builder隐藏了。  
  Builder还把构造代码跟表示代码相分离，提高了对象的模块性。另外，我们也可以更精细地控制对象的构造。
3. Abstract Factory和Builder区别在于前者着重于多个系列的产品的构建、产品是立即返回的；后者着重于逐步  
  构建复杂对象、最后一步返回对象。


**设计模式**（可复用面向对象的基础） 82-103 Jul 14<br>
*工厂方法、原型、单例模式*
1. 工厂方法很简单，为了让子类决定实例化哪个类，抽象出一个创建对象的接口。常见于框架中，用抽象类定义  
  和维护对象之间的关系。主要包括Creator和Product接口，Creator的实现类定义工厂方法返回具体产品。
2. 原型模式：用原型实例指定创建对象的种类，通过拷贝来创建新的对象。它包括一个有clone方法的Prototype  
  接口，其实现类通过该方法克隆自身，由客户调用达到创建对象的目的。其优点包括可运行时增删产品、可以  
  改变值或结构以指定新的对象、简化子类构造。
3. 单例模式：将单一实例的创建封装到类中，可以自由决定什么时候、如何创建这个实例。跟全局变量比，单例  
  模式避免了namespace的污染，可以通过子类来精化实例的配置，它内部也可以创建多实例。
4. 总结一下前面介绍的3个创建型模式：
   1. Factory Method，常作为一种标准的创建对象的方法，比较简单，缺点是产品类增加=>工厂类增加。
   2. Abstract Factory，适用于有层次的工厂类，创建一系列产品，缺点是更复杂，增加产品=>修改所有工厂  
     类添加对应方法。
   3. Prototype，适用于创建过程用到原型拷贝的场景。


**设计模式**（可复用面向对象的基础） 105-132 Jul 15<br>
*结构型模式之Adaptor、Bridge、Composite*
1. Adaptor：将一个类的接口转成客户希望的另一个接口，使接口兼容可一起工作。是一种类对象结构型模式。  
  比如绘图编辑器中，TextView和Shape接口不兼容，为了用统一的接口绘出两种内容，可以增加TextShape  
  类，它继承Shape接口和TextView的实现，将TextView实例作为自己的组成部分。TextShape对上屏蔽了与上  
   面接口本不兼容的TextView，跟其他Shape子类同级，让TextView看起来也能跟Shape一样绘制出来。
2. Bridge：将抽象部分与实现分离，使它们可以独立地变化。前面编辑器的例子已经提到该模式，说的是将不同  
  系统的Window和不同类型的Window进行解耦。
3. Composite：用于将对象组合成树形结构，故而用户可以一致地使用单个对象和组合对象。前面编辑器实例  
  说的图元的组合跟组合的组合都可以当成一样的东西，通过draw方法绘制出来，就是因为组合本身可以迭代  
  组合的集合调用draw方法，外面的调用者不关心组合中到底有多少元素。

**设计模式**（可复用面向对象的基础） 132-146 Jul 16<br>
*结构型模式之Decorator、Facade*
1. Decorator（装饰器、包装器）模式：用于动态地给对象添加额外的职责，比生成子类更为灵活。前面编辑器  
  给Text加滚动条和边框就使用了这个模式，Decorator需要继承被装饰组件的接口，且持有后者实现类的对象。  
  我们可以使用该模式，动态透明地给单个对象添加职责，而不像影响其他对象。
  就添加职责来说，装饰比继承更加灵活，是因为继承需要为每个添加的职责创建一个新的子类。  
  它跟Adaptor的区别是，仅改变对象的职责，不改变其接口。  
  它跟Strategy的区别是，改变对象的外表，不改变内核。而Strategy改变的是对象的内核。
2. Facade（外观/门面）模式：为子系统提供一个一致的界面，定义了一个高层接口，方便了客户使用子系统。
  其优点有：对客户屏蔽子系统组件，使得客户处理的对象少了，更方便使用；子系统跟客户解耦；我们可随意  
  使用子系统类，有利于在系统易用性和通用性间做权衡。  
  Facade可以跟抽象工厂模式一起使用，后者提供子系统相关的对象，对Facade隐藏平台相关的类。

**设计模式**（可复用面向对象的基础） 146-165 Jul 17<br>
*结构型模式之Flyweight、Proxy*
1. Flyweight（享元）模式：通过共享来支持大量细粒度对象。典型的例子就是文档编辑器打开的文档，如果每个  
  字符都对应一个对象，则会导致大量内存消耗和运行开销。解决办法是让每种字符对应一个flyweight对象，这  
  会形成对象池，对象只包含内部状态，而外部状态如字体放在Context中，客户调flyweight时将context传递给  
  flyweight。flyweight的创建应该交给FlyweightFactory做，以保证共享。
2. Proxy模式：为对象提供一种代理来控制对这个对象的访问。结构很简单，Proxy跟被代理者实现同一接口，客  
  户使用proxy对象，该对象可适时地向被代理者转发请求。
3. 结构型模式的比较：
   1. Adapter和Bridge的共同点是，都给对象提供了一定程度的间接性，从别的接口向目标对象转发请求。  
    不同点是，Adaptor目的是解决两个已有接口的不匹配问题，不用考虑接口怎样实现的及其演化；而Bridge  
    让接口与接口的实现进行桥接，为用户提供一个稳定的接口，还会在系统演化是适应新的实现。
   2. Adapter跟Facade相比最大的区别是，Adapter复用一个原有的接口，Facade会定义新接口。
   3. Decorator跟Composite模式的共同点是递归组合，结构图也很像。不同点是，Decorator旨在给对象添加  
   额外职责，避免了静态实现所有功能组合导致的子类剧增；Composite旨在构造类，让多个相关对象可被  
   统一地处理，重在表示而非修饰。
   4. Decorator跟Proxy相比，可以动态地添加和分离职责。Proxy的意图是，在不方便直接访问一个实体时，  
   为该实体提供一个替代者，访问其关键功能。Proxy比Decorator更具有确定性——要能在编译时确定对象  
   的全部功能。

**设计模式**（可复用面向对象的基础） 166-183 Jul 18<br>
*行为型模式之责任链、命令*
1. 行为型模式：涉及算法和对象间职责的分配，不仅描述对象和类的模式，也描述它们之间的通信模式。
2. 责任链模式：一种对象行为型模式，旨在使多个对象有机会处理请求，避免请求的发送者与接受者之间的耦合  
  关系。这些对象被连成一条链，请求沿着链传递、处理。链具有动态性，故增强了给对象指派职责的灵活性。  
  结构：Handler接口包含handleRequest方法，它的实现类可被实例化成为多个对象，来处理请求。每个对象都  
  可以转发请求给后继者处理。典型场景是Interceptor处理HttpRequest，前者就对应Handler。
3. 命令模式：一种对象行为型模式，旨在将请求封装成对象，让不同请求对客户进行参数化，可支持撤销操作。  
  结构：接口Command有execute方法，其实现类的对象可以持有（并调用）一组Command对象。  
  为了支持撤销和重做操作，可以增加unexecute方法，Command保存execute前的状态供撤销使用。

```puml
Factory <|-- FirstFactory : 实现

abstract class Factory{
  +abstract Car createCar()
}
FirstFactory : +createCar()

```

**设计模式**（可复用面向对象的基础） 183-212 Jul 21<br>
*行为型模式之解释器、迭代器、中介*
1. 解释器模式：一种类行为模式，其中解释器的定义是为了解释某种语言的文法。  
  比如，针对正则表达式`raing & (dogs | cats)`，可定义包含interpret方法的抽象类RegularExpression，    
  其子类LiteralExpression用来检查输入是否匹配它定义的字，AlternationExpression检查输入是否匹配任意一  
  个选项，RepetitionExpression将检查输入是否含有多个重复表达式。抽象一下，Expression的子类分两种，  
  一是TerminalExpression——终结符表达式，实现与文法中的终结符相关联的解释操作，每个终结符都需要  
  一个实例，例如literal；二是NonTerminalExpression——文法中每条规则都需要一个实例。该模式可用多种  
  操作来解释一个句子。使用的场景是，文法简单且性能不是问题。
2. 迭代器模式：对象行为型，提供一种方法顺序访问一个聚合对象中的各元素，不暴露该对象的内部表示。  
  结构上，Iterator跟Aggregate是两个独立的接口，后者包含createIterator方法以创建一个iterator。Aggregate  
  可以有多种实现且分别创建不同的Iterator子类对象。迭代器对象跟踪聚合中的对象，并且能计算后继对象。  
  其好处有：一、支持不同遍历顺序；二、简化聚合接口；三、同一聚合上可有不同迭代器同时进行遍历。 
3. 中介模式：对象行为型，用中介对象封装一系列的对象交互，使得各对象不需要显式相互引用，解耦的同时  
  也可以独立改变它们之间的交互。结构如下：
   ```puml
    interface Mediator
    interface Colleague
    Mediator <|-- ConcreteMediator
    Mediator <- Colleague : mediator
    Colleague <|--- ConcretColleague1 
    Colleague <|-- ConcretColleague2 
   ConcreteMediator --> ConcretColleague1
   ConcreteMediator --> ConcretColleague2 
   ```
   
   中介模式跟Facade很像，都封装了一些职责。区别是，Facade将对象子系统抽象出接口，单向对外服务。  
  而中介提供了各Colleague对象不支持的协作行为，是多向的。缺点是中介可能变得很复杂。

   
**设计模式**（可复用面向对象的基础） 212-227 Jul 21<br>
*行为型模式之Memento、Observer*
1. Memento（备忘录）模式：为了记录一个对象的状态信息，且该信息可以用于恢复其对象，我们将封装的  
  状态称为备忘录。备忘录是Originator创建给客户的，Originator也可以编程备忘录对应的状态。其优点是  
  Originator的结构没有暴露给用户。可配合Command模式帮其undo/redo改变状态。结构如下：
   ```puml
    class Originator {
     state      
    setMemoto(Memento m)
    createMemento() 
   } 
   class Memento {
     getState()
     setState()
     state
   }
   Originator .> Memento
   Memento <--o Caretaker
   note right of Caretaker: 保存备忘录，\n但不能操作其内容
   note right of Memento: 存储Originator状态
   note bottom of Originator: 创建备忘录表示状态，\n用备忘录恢复状态

   ```
2. Observer（观察者）模式：定义了对象间一对多的依赖关系，一个对象状态改变后，会通知其所有依赖者。  
  其中的状态会变化的关键对象是目标，那些依赖者就是观察者。这种交互也叫发布-订阅。结构如下：
   ```puml
    interface Subject{
    observers
    attach(Observer o)
    detach(Observer o)
    notify()
   }
   interface Observer{
   update()
   }
   class ConcreteSubject{
   getState()
   setState()
   }
   class ConcreteObserver{
   observerState
   update()
   }
   Subject --> Observer : observer
   Subject <|-- ConcreteSubject
    Observer <|-- ConcreteObserver
    ConcreteSubject <-- ConcreteObserver::update
   
   note right of ConcreteObserver: 获取subject新的状态
   note right of Subject : 调观察者的update以通知，\n也可添加/删除观察者
   note right of ConcreteSubject : return subjectState 
   ```
   优点：目标和观察者抽象耦合，目标知道其观察者们，可跟它们通信，二者可在不同的层次；支持广播。  
  缺点：观察者们不知道其他观察者的存在，可能不知道改变目标的代价，目标上一个小变化有可能会导致  
 一系列观察者的更新。


**设计模式**（可复用面向对象的基础） 227-241 Jul 23<br>
*行为型模式之State、Strategy（均为对象型）*
1. State模式：允许一个对象在其状态改变时行为也发生改变。比如TCP连接状态的不同，决定了如何请求。  
  为了方便状态间的转换换，以及添加新的状态，可以让State负责**改变Context的state对象**。结构如下：
   ```puml
   class Context{
    -state
    +setState()
    +getState()
    +request()
   }
    interface State{
   +handle()
   }
   Context o-> State
   
   note bottom of Context : state.handle()
   
   class ConcreteStateA{
      +handle()
   }
   class ConcreteStateB{
      +handle()
   }
   State <|-- ConcreteStateA
   State <|-- ConcreteStateB
   ```
   Context是客户使用的主要接口，客户可以给它设置一个state对象，后面只与Context交互。
2. Strategy模式：封装算法为对象，它们可以相互替换，使得算法可以独立于客户而变化。结构如下：  
   ```puml
   class Context{
    +execute()
   } 
   interface Strategy{
     +doOperation()
   }
   class StrategyA{
   +doOperation()
   }
   
   class StrategyB{
   +doOperation()
   }
   Strategy <|-- StrategyA
   Strategy <|-- StrategyB
   
   Context o- Strategy
   
   note bottom of Context : strategy.doOperation()
   ```
   客户可以创建传递Strategy对象给Context，然后只与Context交互，省去了if…else…。当然，缺点是客户要  
  知道Strategy的实现类以及如何选用，也增加了对象数目。

**设计模式**（可复用面向对象的基础） 242-260 Jul 24<br>
*行为型模式之模板方法、访问者*
1. 模板方法模式：一种类行为型模式，抽象类将固定的操作步骤放到方法中，步骤可以作为单独的抽象方法，  
  延迟到子类实现。如果父类中步骤默认是空实现，那就称为钩子操作。
2. 访问者模式：一种对象行为型模式，表示一个操作，它作用于某对象结构中的各个元素，在不改变元素的类  
  的前提下，该模式可以定义作用于这些元素的新操作。结构如下：
   ```puml
   interface Visitor {
   +visitA(A)
   +visitB(B)
   }
   class ConcreteVisitorA{
   +visitA(A)
   +visitB(B)
   }
   
   class ConcreteVisitorB{
   +visitA(A)
   +visitB(B)
   }
   Visitor <|-- ConcreteVisitorA
   Visitor <|-- ConcreteVisitorB
   
   interface Element {
   +accept(Visitor)
   }
   class ConcreteElementA{
   +accept(Visitor)
   +operationA()
   }
   
   class ConcreteElementB{
   +accept(Visitor)
   +operationB()
   }
   Element <|-- ConcreteElementA
   Element <|-- ConcreteElementB
   object client
   client --> Element
   client -> Visitor
   note right of ConcreteElementA : accept会调用visitor.visitA(this)
   ```
3. 行为型模式到此就说完了，总结：
   1. **封装变化**是很多模式的主题，例如strategy封装算法，state封装与状态相关的行为，mediator封装对象间  
    的通信协议，Iterator封装了访问/遍历一个集合对象中的各构件的方法。
   2. **对象作为参数**，如visitor是一个多台的accept操作的参数，操作作用于被访问的对象。有些模式比如  
   Command中请求作为对象，memento中内部状态作为对象，两种对象都相当于令牌，可以到处传递。
   3. **分布和封装通信**：Mediator封装其他对象的通信，Observer则引入目标、观察者分布化通信。前者耦合  
    较紧但是连接结构好理解，后者反之。
   4. **解耦发送者和接受者**：避免直接相互引用，让合作的对象的关系更松。比如Command对象定义sender  
    和receiver间的绑定关系，换receiver也可以继续工作。有数据依赖的，观察者模式可以很好地解耦……

**设计模式**（可复用面向对象的基础） 完结 Jul 25<br>
*行为型模式之模板方法、访问者*
1. 本书做的是 把现有的面向对象设计中的模式加以文档化，分类整理并赋予名称定义。抽象出一套描述语言，  
  让系统中的设计更易理解，更易探索学习（跟逆向工程比），也让围绕设计模式的沟通更简洁高效。
2. 在面向对象的系统的设计中，设计模式是一种补充——从OOA到OOD产生的对象可能并不足以实现灵活、可  
  复用的系统，还可能要添加一些新的对象，设计模式为添加新对象提供指导。
3. 面向对象软件的生命周期分为：原型阶段、扩展阶段、巩固阶段。在原型上加新需求直至满足基本需求后，  
  是青春期，其后续演化、扩展需要尽量满足俩要求：a.能满足更多需求；b.更易于复用。俩要求互相矛盾，  
  需求导致其逐渐臃肿，直到某一时刻，软件的类层次已经无法与问题域匹配。这时就要重构，将曾经变大的  
  类分割回小的，专用或通用的构件会被分开，曾基于继承的白箱复用更多变为黑箱复用。生成了新原型……
  虽然这个循环不能避免，但设计模式可以帮助让设计更稳定，让循环慢一点。
