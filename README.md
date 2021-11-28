# Property Based Testing Evaluation

In the past I did a lot of unit and integration testing in Java. It was easy to reason about the
imperative process of services (TODO: What do you mean???). What I was missing out was the
possibility to reason about rules or properties that my software should follow to conform to the
business requirements.

After reading a lot about functional programming I stumbled over the term "Property Based Testing"
aka PBT.

The basic idea:

1. Specify rules that the software under test should follow.
2. And verify this behavior with generated tests like in the 100 or 1.000 instead of at most ten
   examples like I used to do in the past.

As I first asked about experiences on PBT in my company I found that it is still not widely known in
the Java community. Even if the first appearance took place in 2000 in a paper by

The first steps of PBT were taken at the beginning of this milenium and was presented by a paper of
Coen Claessen and John Hughes. And on the other hand I heard things like:

* "I already use specification by example. So why jet another framefork to learn?"
* "What is the benefit of this? - I already have a very high test coverage."

So basically I will motivate my line of thought why I think it is worth it to invest into the ideas
and the learning of PBT.

## Examples

In a microservice driven world there is a lot of communication going on between the different
systems and components. And perhaps you use events to trigger actions and these events are
represented in format JSON as they were sent back and forth between components.

So my goal is to setup an event wrapper that can be used to represent different business events but
has a common wrapper for all of my business events.

The JUnit approach first.

* present EventWrapperTest and EventWrapper -> looks fine to me

Now lets start to look into PBT.

looks good to me. Even the size restriction of the basic json is verified.

- I know some will now shout out something like I know better!
  And there is always something to improve and things to improve. But bare with me for a moment
  longe

## Lessons Learned

But why yet another testing framework?

What did I learn by diving into PBT?

It is never a good idea to verify the behavior of a function or service by using the same components
within the tests that should verify it.

If a test is as complicated as the system under test this is never a good sign.

## Resources

For all the examples I use jqwik provided by Johannes Link, who is well known in the java testing
community.

__Articles about PBT and usage__:

* [How to Specify it! In Java! | How to Specify It! In Java!](https://johanneslink.net/how-to-specify-it/)
* [Property-based Testing in Java: Introduction - My Not So Private Tech Life](https://blog.johanneslink.net/2018/03/24/property-based-testing-in-java-introduction/?source=:em:nw:mt::::RC_WWMK200429P00043C0046:NSL400200167)
* [johanneslink](https://blog.johanneslink.net/2018/07/16/patterns-to-find-properties/)
* [johanneslink 2](https://blog.johanneslink.net/2018/07/16/patterns-to-find-properties/#pattern-business-rule-as-property)
* [Property-based Testing Patterns](https://blog.ssanj.net/posts/2016-06-26-property-based-testing-patterns.html)
* [Know for Sure with Property-Based Testing](https://blogs.oracle.com/javamagazine/post/know-for-sure-with-property-based-testing?source=:em:nw:mt::::RC_WWMK200429P00043C0046:NSL400200167&elq_mid=208248&sh=1712092020221515182213312809073514&cmid=WWMK200429P00043C0046)
* [johanneslink 3](https://johanneslink.net/downloads/pbt-workshop-english.pdf)

__The Framework__:

* [jqwik Users Guide](https://jqwik.net/docs/current/user-guide.html)
* [jlink/jqwik-spring: jqwik extension to support testing of Spring and Spring-Boot applications](https://github.com/jlink/jqwik-spring)
  -> spring boot support: (example):
* [jqwik-samples/jqwik-spring-boot-gradle at main · jlink/jqwik-samples](https://github.com/jlink/jqwik-samples/tree/main/jqwik-spring-boot-gradle)

Interesting series about testing:

TODO: be more descriptive why this is interesting!

* [hashnode1](https://sergiosastre.hashnode.dev/multiplying-the-quality-of-your-unit-tests-part-1)
* [hashnode2](https://sergiosastre.hashnode.dev/multiplying-the-quality-of-your-unit-tests-part-2)
  Pros and Cons (kotlin code examples)

[arothuis](https://arothuis.nl/posts/property-based-testing-rock-paper-scissors/)
-> nice starter - academic approach

Examples based on json date conversion requirements - this article actually triggered my ideas for
this project.

* [blogspot](https://aredko.blogspot.com/2020/02/)
* [Differences Between ZonedDateTime and OffsetDateTime | Baeldung](https://www.baeldung.com/java-zoneddatetime-offsetdatetime)
  Recommendation: Use OffsetDateTime for database use.
* [Jackson , java.time , ISO 8601 , serialize without milliseconds | Newbedev](https://newbedev.com/jackson-java-time-iso-8601-serialize-without-milliseconds)
* [Jackson JSON - Using @JsonSerialize and @JsonDeserialize with Converter for custom conversion](https://www.logicbig.com/tutorials/misc/jackson/json-serialize-deserialize-converter.html)
* [JSON deserialize generic types using Gson and Jackson | JSBlogs](https://blogs.jsbisht.com/blogs/2016/09/07/json-deserialize-generic-types-using-gson-and-jackson)
* [Step by Step to Property based Testing - Dave Nicolette 2018](https://www.leadingagile.com/2018/04/step-by-step-toward-property-based-testing/)
*

## The Story Line

Here I will discribe the emerging structure of my article.

## Ideas and Citations

* Why should I invest in this?
* What will this provide if only finding academic examples about simple data structures?

One answer to this:  
Think outside of the box. Find ways to question your system under test to get a deeper insight of
the functioning of the system.  
On the other hand exploring new areas might lead to new ways of thinking and progressing in the
realm of problem solving. You can extend your repertoire/portfolio of tools to use for solving
different kind of problems/challenges.

	If the only tool you have at hand every problem is a nail. 
		Unknown - at least to me

* Where it comes in handy

Most of the time in live it is not about the easy parts but about challenges and errors.  
The process of shrinking is used in case a theory fails with errors that contradict the property
assumptions made. Shrinking now tries to find the simplest example within the data domain to
reproduce the error. And this can ease up debugging and fixing the error a lot especially if the
data structures are quite elaborate.

There could be a sorting algorithm for a specific data structure at hand. And with PBT you might be
able to find counter examples that break the validity of this algorithm. - Might - It's not like
free in "free beer" but it comes with the cost of experimenting and exploring a lot to get a good
notion what is possible and what not and when to use it.

## Generation of Test Data

How to start with PBT?  
Basically the easiest way is just to start with example based tests as you already are used to do
with JUnit5 or Spock framework.

## My Framework of Choice to Explore PBT

I selected the famework jqwik maintained by Johannes Link.  
In contrast to the older and more widely used framework JUnit-quickcheck jqwik is integrated into
the JUnit5 platform concept and was implemented as test engine. In my opinion this is a more future
prove approach.

[JUnit QuickCheck](https://github.com/pholser/junit-quickcheck)

## Patterns that are designated for PBT

There are some patterns that come in handy if you are not sure if your problem is a nail.  
So I curated three of these patterns just as an appetizer.

## Other interesting Possibilities

It does not need always to be the most complicated or sophisticated tool. Most of the time there are
easier to use solutions. So look into e.g. Data Driven Testing. Myself I developed and maintained
this kind of tool from 2004-2011 [DDTUnit - Sourceforge](https://sourceforge.net/projects/ddtunit/).
With this kind of tooling you can setup parametrized tests with a multitude of examples to
process/test.  
One of my favorites today is Spock testing framework that provides a nice table like notation.
Junit5 also provides advanced ways to inject provider-driven data within parametrized tests.

	What is property-based testing (PBT), anyway?
	
	The basic idea is to validate an expected behavior of a system (a property of the system) against 
    a range of data points. This is in contrast to example-based testing, which is the basis of most 
    unit testing and microtesting. An example-based test case is, as the name implies, a single concrete 
    example of an expected behavior of the system under test\
	...
	There is another reason to consider PBT. The tools don’t just run examples. They also apply logic 
    to the test results to reduce the number of unique test cases necessary to disprove a theory. If 
    you’re working with legacy code, as most of us do most of the time, this offers a useful tool for 
    exploring the behavior of an application without having to write a large number of example-based 
    characterization tests.
	...
	Theories
	
	A step further than data-driven testing is the notion of theories. Libraries are available that help 
    you test theories about the general behavior of applications. This is baked into PBT, but can also 
    be done without the additional features offered by PBT tools. You can use the same libraries the 
    PBT tools use independently.
	...
	Generating test data
	
	One of the aspects of PBT that distinguishes it from data-driven testing and theories is the generation 
    of pseudo-random test data. As mentioned above, PBT tools are able to generate data for basic data types, 
    but for custom data types we must write our own generators.
	...
		Conclusion
	
	Some lessons I’ve learned from this exercise:
	
	* PBT can be useful for functionality that has certain characteristics, such as numerous different 
        combinations of inputs that may lead to unexpected behaviors, difficult to test through example-based 
        testing alone.
	* The cost of setting up PBTs is significantly higher than that of setting up conventional example-based 
        test cases, where “cost” is measured in terms of effort, amount of code, and potential for error 
        in the test cases themselves as opposed to the application under test. (It took me nine hours to 
        complete this learning exercise, and the result is still not entirely satisfactory. It will probably 
        take another nine hours to wrangle it all into shape, including application enhancements. At the end 
        of all that, there will be just one test case.) Therefore, PBT should be used prudently where it adds 
        value, and not applied across the board as a dictated “standard practice.”
	* The algorithmic complexity of the code under test is not the decision factor for choosing to do PBT. 
        Rather, it’s the number of combinations of input values. It would be useful for developers to 
        cultivate skills in combinatorial testing to help them craft the right number of PBT cases covering 
        the right conditions.
	* The shrinking feature of PBT tools is one of the most compelling reasons to learn them, especially 
        if we must explore the functionality of unfamiliar, existing code bases.
		Dave Nicolette

TODO:    Example on FiveCardStud Poker by Dave Nicolette - compare with Włodek Krakowski refactoring
examples on https://refactoring.pl - can these be combined?  
-> Refactoring, Clean Code and PBT in one example?   
==> definitly worth a try ;-)
