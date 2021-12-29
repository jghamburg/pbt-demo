SLIDES := $(patsubst %.slides.md,%.md.slides.pdf,$(wildcard *.md))
HANDOUTS := $(patsubst %.slides.md,%.md.handout.pdf,$(wildcard *.md))

all : $(SLIDES) $(HANDOUTS)

%.md.slides.pdf : %.slides.md
	pandoc $^ -t beamer --slide-level 2  --pdf-engine=xelatex -o $@

%.md.handout.pdf : %.slides.md
	pandoc $^ -t beamer --slide-level 2  --pdf-engine=xelatex -V handout -o $@ 
	pdfnup $@ --nup 1x2 --no-landscape --keepinfo \
 		--paper letterpaper --frame true --scale 0.9 \
 		--suffix "nup"
	mv $*.md.handout-nup.pdf $@
		

clean : 
	rm -f *.pdf
