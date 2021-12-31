# Property based Testing  

## The Article  

An article and example project to explore property based testing within the Java eco system.  

Using the fantastic framework jqwik from Johannes Link and some other great references from the open source community.  

## The Presentation  

The associated presentation uses pandoc and reveal.js on basis of markdown documentation.  

```bash
pandoc --incremental --to=revealjs --standalone \
  --output=build/my.slides.html docs/my.slides.md \
  -V revealjs-url=https://unpkg.com/reveal.js@4.2.1/\
  -V theme=cube \
  -V transition=league 
#
open build/my.slides.html
```

To generate the pdf handouts I followed the description of Lincoln Mullen.

```bash
pandoc --to=beamer --slide-level 2  --pdf-engine=xelatex \
  --output=build/my.slides.md.pdf  docs/my.slides.md 
# and handout
pandoc --to=beamer --slide-level 2  --pdf-engine=xelatex -V handout \
  --output=build/my.slides.md.handout.pdf docs/my.slides.md 
pdfnup build/my.slides.md.handout.pdf --nup 1x2 --no-landscape --keepinfo \
  --paper letterpaper --frame true --scale 0.9 \
  --suffix "nup"
```

## Open Issues - Next Research Steps  

As this is just a starting point in my journey to get a good grasp of PBT here are some ideas to explore  

* how to incorporate the encoding and decoding into the Springboot Messaging context
* how to use PBT to examine the stability of my RestControllers by providing generated input data
* how difficult will it be to generate complex business objects like a sales order containing customer details, order positions and other relevant stuff
* is it possible to work with this kind of generated data in a consistent way throughout development of new functionality

__Technical Stuff to Explore__  

* can I switch to gradle as my one and only build system and discard separate cli calls?  
* what about using Asciidoctor to incorporate my source code directly into my presentation and article

## References  

### Presentations and Handouts  

* [pandoc - reveal.js example](https://garrettgman.github.io/rmarkdown/revealjs_presentation_format.html)
* [Lincoln Mullens Blog - PDF slides and handout using Pandoc and Beamer](https://bl.ocks.org/lmullen/c3d4c7883f081ed8692a)
* [Alexey Gumirov - Pandoc beamer how to](https://github.com/alexeygumirov/pandoc-beamer-how-to)

## Addendum  

### Required Software for Presentation  

I am using a macbook pro. So the installation details are restricted to this platform.  

```bash
brew install basictex
brew install pandoc
pip install pdfnup
```

