# electron-javalin
electron + solidjs - javalin + kotlin

STUDY용

Scope Functions
객체를 사용할 때 Scope(범위, 영역) 를 일시적으로 만들어서 속성(property)나 함수를 처리하는 용도로 사용되는 함수이다. 간편한 코드 사용과 가독성, 빌더 패턴의 이용, 부가적인 후처리 등을 하는 데에 도움을 준다.

let
with
run
apply
also

1. let
```
fun <T, R> T.let(block: (T) -> R): R
```

let 함수는 매개변수화된 타입 T의 확장 함수이다.(extension) 자기 자신을 받아서 R을 반환하는((T) -> R) 람다 식을 입력으로 받고, 블럭 함수의 반환값 R을 반환한다. 여기서는 Person 클래스의 확장 함수로 사용되어 person.let 의 형태가 가능해진다.

let 함수를 사용하면 객체의 상태를 변경할 수 있다.
```
val person = Person("", 0)
val resultIt = person.let {
    it.name = "James"
    it.age = 56
    it // (T)->R 부분에서의 R에 해당하는 반환값.
}

val resultStr = person.let {
    it.name = "Steve"
    it.age = 59
    "{$name is $age}" // (T)->R 부분에서의 R에 해당하는 반환값.
}

val resultUnit = person.let {
    it.name = "Joe"
    it.age = 63
    // (T)->R 부분에서의 R에 해당하는 반환값 없음
}

println("$resultIt")
println("$resultStr")
println("$resultUnit")


// Person(name=James, age=56)
// Steve is 59
// kotlin.Unit
```
블럭의 마지막 return 값에 따라 let의 return 값 형태도 달라지는 모습을 보인다.


또한 T?.let { } 형태에서의 let 블럭 안에는 non-null 만 들어올 수 있어 non-null 체크 시에 유용하게 쓸 수 있다. 객체를 선언하는 상황일 경우에는 elvis operator(?:) 를 사용해서 기본값을 지정해줄 수도 있다.
```
val nameStr = person?.let { it.name } ?: "Defalut name"
```

2. with
```
fun <T, R> with(receiver: T, block: T.() -> R): R
```
with는 일반 함수이기 때문에 객체 receive를 직접 입력받고, 객체를 사용하기 위한 두 번째 파라미터 블럭을 받는다. T.()를 람다 리시버라고 하는데, 입력을 받으면 함수 내에서 this를 사용하지 않고도 입력받은 객체(receiver)의 속성을 변경할 수 있다.

즉, 아래 예제에서 with(T) 타입으로 Person을 받으면 {} 내의 블럭 안에서 곧바로 name 이나 age 프로퍼티에 접근할 수 있다. with는 non-null의 객체를 사용하고 블럭의 return 값이 필요하지 않을 때 사용한다. 그래서 주로 객체의 함수를 여러 개 호출할 때 그룹화하는 용도로 활용된다.
```
val person = Person("James", 56)
with(person) {
    println(name)
    println(age)
    //자기자신을 반환해야 하는 경우 it이 아닌 this를 사용한다
}

// James
// 56
```

3. run
run은 두 가지 형태로 선언되어 있다. 먼저 첫 번째는 이렇게 생겼다.
```
fun <T, R> T.run(block: T.() -> R): R
```
run은 with처럼 인자로 람다 리시버를 받고, 반환 형태도 비슷하게 생겼지만 T의 확장함수라는 점에서 차이가 있다. 확장함수이기 때문에 safe call(.?)을 붙여 non-null 일 때에만 실행할 수 있다. 어떤 값을 계산할 필요가 있거나 여러 개의 지역변수 범위를 제한할 때 사용한다.
```
val person = Person("James", 56)
val ageNextYear = person.run {
    ++age
}

println("$ageNextYear")

// 57
```


두 번째 run 선언은 다른 형태를 띤다.

```
fun <R> run(block: () -> R): R
```
이 run은 확장 함수가 아니고, 블럭에 입력값도 없다. 따라서 객체를 전달받아서 속성을 변경하는 형식에 사용되는 함수가 아니다. 이 함수는 어떤 객체를 생성하기 위한 명령문을 블럭 안에 묶음으로써 가독성을 높이는 역할을 한다.
```
val person = run {
    val name = "James"
    val age = 56
    Person(name, age)
}
```


4. apply
```
fun <T> T.apply(block: T.() -> Unit): T
```

apply는 T의 확장 함수이고, 블럭 함수의 입력을 람다 리시버로 받았기 때문에 블럭 안에서 객체의 프로퍼티를 호출할 때 it이나 this를 사용할 필요가 없다. run과 유사하지만 블럭에서 return 값을 받지 않으며 자기 자신인 T를 반환한다는 점이 다르다.

```
val person = Person("", 0)
val result = person.apply {
    name = "James"
    age = 56
}

println("$person")

//Person(name=James, age=56)
```

앞에서 살펴 본 let, with, run 은 모두 맨 마지막 반한되는 값은 R이었다. 하지만 apply와 아래에서 살펴볼 also는 T를 반환한다.

예시로, let에서는 resultUnit의 블럭 내에서 반환되는 값이 없어 최종적으로 Unit 타입이 되었지만, apply에서는 마지막에는 객체 자기 자신(T)을 반환하기 때문에 result는 Person 타입이 되었다. 그래서 println을 실행했을 때에 person의 내용이 콘솔에 찍히는 것을 확인할 수 있다.

객체의 함수를 사용하지 않고 자기 자신을 다시 반환할 때에 사용되기 때문에, 객체의 초기화나 변경 시에 주로 사용된다.



5. also
```
fun <T> T.also(block: (T) -> Unit): T
```

also는 T의 확장함수이고, 블럭 함수의 입력으로 람다 리시버를 받지 않고 this로 받았다. apply와 마찬가지로 T를 반환한다.
```
val person = Person("", 0)
val result = person.also {
    it.name = "James"
    it.age = 56
}

println("$person")

//Person(name=James, age=56)
```

블럭 함수의 입력으로 T를 받았기 때문에 it을 사용해 프로퍼티에 접근하는 것을 볼 수 있다. 그래서 객체의 속성을 전혀 사용하지 않거나 변경하지 않고 사용하는 경우에 also를 사용한다.

객체의 데이터 유효성을 확인하거나, 디버그, 로깅 등의 부가적인 목적으로 사용할 때에 적합하다.
```
val numbers = arrayListOf("one", "two", "three")
numbers
    .also { println("add 하기 전에 print: $it") }
    .add("four")
```
