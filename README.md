# Property based Testing  

## The Article  

An article and example project to explore property based testing within the Java eco system.  

Using the fantastic framework jqwik from Johannes Link and some other great references from the open source community.  

## The Presentation  

The associated presentation uses pandoc and reveal.js on basis of markdown documentation.  

```bash
theme=league
transition=cube
pandoc -t revealjs -s -o build/myslides.html docs/myslides.md \
  -V revealjs-url=https://unpkg.com/reveal.js@3.9.2/\
  -V theme=$theme \
  -V transition=$transition
open build/myslides.html
```

## References  

[pandoc - reveal.js example](https://garrettgman.github.io/rmarkdown/revealjs_presentation_format.html)


