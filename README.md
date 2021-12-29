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
  --output=build/my.slides.md.handout.pdf docs/my-slides.md 
pdfnup build/my.slides.md.handout.pdf --nup 1x2 --no-landscape --keepinfo \
  --paper letterpaper --frame true --scale 0.9 \
  --suffix "nup"
```

## References  

[pandoc - reveal.js example](https://garrettgman.github.io/rmarkdown/revealjs_presentation_format.html)
[Lincoln Mullens Blog - PDF slides and handout using Pandoc and Beamer](https://bl.ocks.org/lmullen/c3d4c7883f081ed8692a)

