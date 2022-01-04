---
title: "Property based Testing"
subtitle: "An Experiment for good"
author: Jörg Gellien
date: January 22, 2022
titlepage-note: |
  This is a the note that goes on the title page. This talk is to be given at OTTO showcase.
keywords: [nothing, nothingness]
abstract: |
  This is the abstract.

  It consists of two paragraphs.
fontsize: 17pt
output:
  revealjs::revealjs_presentation:
    theme: moon
    highlight: coderay
    center: false
    transition: fade
    self_contained: true
    reveal_options:
      slideNumber: true
      previewLinks: true
---

# Property based Testing aka PBT

## Motivation {data-transition="zoom" data-background=#008811}

- a lot of experience in writing example based tests
  * most of the time easy to reason about
- Missed out ideas and possibilities to verify properties or rules of system bwhavior
  - are there invariants within my function under test? - like if you sort a list the length of result stays the same.
- in Java community Property Based Testing aka PBT not widely known


::: notes  
This is a note page and you ought to be able to tell.
:::

## Rules of the Game {data-transition="fade-in slide-out"}

1. Specify rules or properties that the software under test should follow.
2. Verify this behavior with generated test data like in the 100 or 1.000 instead of at most five examples like I used to do.

::: notes  
I am sure about this point.
:::

## The Example Class  

```java
@Value
@With
public class EventWrapper<T> {
  String eventId;
  String eventType;
  OffsetDateTime eventTime;
  T data;
}
```

and the business event providing deeper.

```java
@Value
public class BusinessEvent {
  String firstValue;
  String secondValue;
}
```

::: notes  
* One wrapper class to hold common attributes for all events
* Generic connection to the business event details
* encode these event objects into and from the JSON representation  
:::

## Why this Example  

* In recent years I developed services based on microservice architecture.  
* The communication patterns is based on messaging infrastructure using JSON messages.  
* This pattern is reasonable well known in the audience - easy to follow

# The Demo 

## The Experiences so far  {data-transition="convex"}

* There seems to be a problem with the provided type BusinessEvent.
* It found counter example on the first attempt - nice
* The counter example looks quite nicely reduced with interesting values I never planed to use in my production code
* What is this about Shrunken example and Original example?

## The Shrinking Effect  

Like in a lot of other disciplines PBT shines when finding counter examples that reject the
assertion of a property. PBT introduces the concept of shrinking trying to find the simplest example
to reproduce the error. And this can ease up debugging and fixing the error a lot. 

# Demo Improved  

## What else did I found out {data-transition="concave"}

* With the changes in place there is just one thing left. Comparing the OffsetDateTime correctly.  
This problem never occurred to me in the first place and was not discovered with my examples.  

* Now we have verified the behavior or property with 1000 generated testdata. And on every new run the 
next randomly generated testdata will be used - until a counter example is found.  

# Patterns that are Designed for PBT  

Based on ideas from Johannes Link paper  
[Patterns to Identify Properties by Johannes Link 2018](https://blog.johanneslink.net/2018/07/16/patterns-to-find-properties)

## Inverse Functions

There are a lot of functions that have a reverse. E.g. encode/decode JSON. So basically for all
inputs

```text
ForAll x:encode(decode(x))==x  
```

## Fuzzying  

Code should always be written that it will behave nicely. It should not spit into your face only because unexpected data examples. As examples you could check for:

* No exceptions occur, at least no unexpected ones - wrong data is identified on entering the
  service udner test.
* No 5xx return codes in http requests, maybe you even require 2xx all the time
* All return values are valid

## Test Oracle

There are a lot of functions and algorithms where you want to optimize. So you have to verify that

```text
ForAll x:old(x) == new(x)
```

This kind of verification is called test oracle.  
There are a few sources where the alternatives can come from:

* Simple and slow versus complicated but fast
* Self-made versus commercial
* Old (pre-refactoring) versus new (post-refactoring)

# Lessons Learned  

## What did I learn by diving into PBT?

>
>If the only tool you have at hand is a hammer every problem is a nail.  
>Unknown - at least to me

* Think outside the box. Get a deeper insight of the functioning of the system.  

* On the other hand exploring new areas might lead to new ways of thinking and progressing in the realm of problem-solving. You can extend your repertoire of tools to use for solving different kind of challenges.

## Things that came up during the experiments  

* am I restricting my input data correctly?
* will my system crash under some allowed but bizzar input that never would come to my mind. 
  - Ever tried to use chinese input characters?


* Definitly not for the faint of heart.  
You have to think hard to find properties that represent your system.  
And it can be challenging to describe data generators to prove specific properties.   

::: notes  
* PBT will be a good solution if you find structures and algorithms where your gut feeling
starts to moan and the existing tests do not provide sufficient security and understanding of the system.

* Myself I was quite astonished how many ideas and insights I generated only by providing some unexpected input data.  
:::

# At the End  

## Exploring PBT led to

* a lot of fun
* a cool way to generate test data
* and a new tool for my belt

## Questions

Any Questions or Remarks ?

You are welcome

## References  

For all the examples I use jqwik provided by Johannes Link, who is well known in the java testing community.  

The sample project with the code and my article with a lot more references you can find on 
[github jghamburg/pbt-demo](https://github.com/jghamburg/pbt-demo).  
The associated article is published on [jghamburg.github.io](https://jghamburg.github.io/2022/01/02/001-java-property-based-testing.html)

