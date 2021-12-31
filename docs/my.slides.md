---
title: "Property based Testing"
subtitle: "An Experiment for good"
author: Jörg Gellien
date: January 22, 2022
titlepage-note: |
  This is a the note that goes on the title page. This talk is to be 
  given at OTTO showcase.
keywords: [nothing, nothingness]
abstract: |
  This is the abstract.

  It consists of two paragraphs.
fontsize: 12pt
output:
  revealjs::revealjs_presentation:
    theme: sky
    highlight: coderay
    center: false
    transition: fade
    self_contained: true
    reveal_options:
      slideNumber: true
      previewLinks: true
---

# Property based Testing aka PBT

## Motivation {data-transition="zoom" data-background=#f00f0a}

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

```{.java .number-lines}
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

```{.java .number-lines}
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

::: {.warning}
This is a warning!
:::

* In recent years I developed services based on microservice architecture.  
* The communication patterns is based on messaging infrastructure using JSON messages.  
* This pattern is reasonable well known in the audience - easy to follow

# The Demo {data-background-iframe="https://otto.de"}

## The Experiences so far  {data-transition="convex"}

- Eat spaghetti
- Drink wine

# Demo Improved  

## Going to sleep {data-transition="concave"}

- Get in bed
- Count sheep

