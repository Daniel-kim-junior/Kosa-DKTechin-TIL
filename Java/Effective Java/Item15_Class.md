```
public final class Complex {
  private final double re;
  private final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public double realPart() {
    return re;
  }

  public double imaginaryPart() {
    return im;
  }

  public Complex plus(Complex c) {
    return new Complex(re + c.re, im + c.im);
  }

  public Complex minus(Complex c) {
    return new Complex(re - c.re, im - c.im);
  }

  public Complex times(Complex c) {
    return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
  }

  public Complex divideBy(Complex c) {
    double tmp = c.re * c.re + c.im * c.im;
    return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
  }

  @Override public boolean equals(Object o) {
    if(o == this) return true;

    if(!(o instanceof Complex)) return false;

    Complex c = (Complex) o;

    return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
  }
}
```

이 클래스는 복소수(실수부와 허수부로 이루어진 수)를 표현한다. Object의 메서드 몇개를 재정의했음
실수부와 허수부를 반환하는 접근자 메서드와 사칙연산 메서드를 정의

사칙 연산 메서드들은 인스턴스 자신을 수정 하지않고 새로운 Complex 인스턴스를 만들어 반환한다
이처럼 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍을 함수형 프로그래밍이라고 한다.

이러한 방식으로 프로그래밍 하게 된다면 코드에서 불변이 되는 영역이 넓어진다. 불변 객체는 단순하다. 불변된 객체는 생성된 시점의 상태를 파괴될 때까지 그대로 간직한다. 모든 생성자가 클래스 불변식을 보장한다면 그 클래스를 사용하는 프로그래머가 다른 노력을 들이지 않더라도 영원히 불변으로 남는다.

반면 가변 객체는 임의의 복잡한 상태에 놓일 수 있다. 변경자 메서드가 일으키는 상태 전이를 정밀하게 문서로 남겨놓지 않은 가변 클래스는 믿고 사용하기 어려울 수 있다.

불변 객체는 근본적으로 스레드 safe하여 따로 동기화할 필요 없다. 그 어떤 스레드도 다른 스레드에 영향을 줄 수 없으니 불변 객체는 안심하고 공유할 수 있다.

따라서 불변 클래스라면 한번 만든 인스턴스를 최대한 재활용하도록 한다.

가장 쉬운 재활용 방법은 public static final을 사용해 자주 쓰이는 값들을 상수로 제공한다.

public static final Complex ZERO = new Complex(0, 0);
public static final Complex ONE = new Complex(1, 0);
public static final Complex I = new Complex(0, 1);

불변 클래스는 자주 사용되는 인스턴스를 캐싱하여 같은 인스턴스를 중복 생성하지 않게 해주는 정적 팩토리를 제공할 수 있다.
박싱된 기본 타입 클래스 전부와 BigInteger가 여기 속한다.

이런 정적 팩토리를 사용하면 여러 클라이언트가 인스턴스를 공유하여 메모리 사용량과 가비지 컬렉션 비용이 줄어든다.
새로운 클래스를 설계할 때 public 생성자 대신 정적 팩토리를 만들어두면, 클라이언트를 수정하지 않고도 필요에 따라 캐시 기능을 나중에 덧붙일 수 있다.
