# Property Based Testing Evaluation

In the past I did a lot of unit and integration testing in Java. It was easy to reason about the
imperative process of services. What I was missing out was the possibility to reason about rules or
properties that my software shoud follow to conform to the business requirements.

After reading a lot about functional programming I stumbled over the term "Property Based Testing"
aka PBT.

The basic idea:

1. Specify rules that the software under test should follow.
2. And verify this behavior with generated tests like in the 100 or 1.000 instead of at most ten
   examples like I used to do in the past.

As I first ask about experiences on pbt in my company I found out that it is still not widely known
in the Java community.

And on the other hand I heard things like:

* "I already use specification by example. So why jet another framefork to learn?"
* "What is the benefit of this? - I already have a very high test coverage."

So basically I will try to motivate why I think it is worth it to invest into the ideas and the
learning of PBT.

## Examples

## Lessons Learned

But why yet another testing framework?

## Resources

For all the examples I use jqwik provided by Johannes Link, how is well known in the java testing
community.

* [How to Specify it! In Java! | How to Specify It! In Java!](https://johanneslink.net/how-to-specify-it/)
* [Property-based Testing in Java: Introduction - My Not So Private Tech Life](https://blog.johanneslink.net/2018/03/24/property-based-testing-in-java-introduction/?source=:em:nw:mt::::RC_WWMK200429P00043C0046:NSL400200167)
* [johanneslink](https://blog.johanneslink.net/2018/07/16/patterns-to-find-properties/)
* [johanneslink 2](https://blog.johanneslink.net/2018/07/16/patterns-to-find-properties/#pattern-business-rule-as-property)
* [jqwik](https://jqwik.net/docs/current/user-guide.html#jqwik-configuration)
* [oracle](https://blogs.oracle.com/javamagazine/post/know-for-sure-with-property-based-testing)
* [Property-based Testing Patterns](https://blog.ssanj.net/posts/2016-06-26-property-based-testing-patterns.html)
* [Know for Sure with Property-Based Testing](https://blogs.oracle.com/javamagazine/post/know-for-sure-with-property-based-testing?source=:em:nw:mt::::RC_WWMK200429P00043C0046:NSL400200167&elq_mid=208248&sh=1712092020221515182213312809073514&cmid=WWMK200429P00043C0046)
* [jlink/jqwik-spring: jqwik extension to support testing of Spring and Spring-Boot applications](https://github.com/jlink/jqwik-spring)
  -> spring boot support: (example):
* [jqwik-samples/jqwik-spring-boot-gradle at main · jlink/jqwik-samples](https://github.com/jlink/jqwik-samples/tree/main/jqwik-spring-boot-gradle)
* [johanneslink 3](https://johanneslink.net/downloads/pbt-workshop-english.pdf)

Interesting series about testing:

* [hashnode1](https://sergiosastre.hashnode.dev/multiplying-the-quality-of-your-unit-tests-part-1)
* [hashnode2](https://sergiosastre.hashnode.dev/multiplying-the-quality-of-your-unit-tests-part-2)
  Pros and Cons (kotlin code examples)

[arothuis](https://arothuis.nl/posts/property-based-testing-rock-paper-scissors/)
-> nice starter - academic approach

Examples based on json date conversion requirements - this article actually triggered my ideas for
this project.

* [blogspot](https://aredko.blogspot.com/2020/02/)
* [Jackson , java.time , ISO 8601 , serialize without milliseconds | Newbedev](https://newbedev.com/jackson-java-time-iso-8601-serialize-without-milliseconds)
* 

