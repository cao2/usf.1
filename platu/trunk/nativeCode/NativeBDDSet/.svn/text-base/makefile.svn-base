include Makefile-64bit.inc

INCLUDE=-I$(CUDD_INCLUDE)

OBJS=$(CUDD_OBJ1) \
		 $(CUDD_OBJ2) \
		 $(CUDD_OBJ3) \
		 $(CUDD_OBJ4) \
		 $(CUDD_OBJ5) \
		 $(CUDD_OBJ6)


linux:  
	g++ -fPIC -c -o temp -I$(JAVA_HOME)/include NativeBDDWrapper.cpp
	g++ -shared -o ../libNativeBDDWrapper.so temp $(OBJS)
	rm temp

mac:
	g++ -c -I/System/Library/Frameworks/JavaVM.framework/Headers NativeBDDWrapper.cpp
	g++ -dynamiclib -o libNativeBDDWrapper.jnilib NativeBDDWrapper.o -framework JavaVM $(OBJS)
	rm -f *.o
