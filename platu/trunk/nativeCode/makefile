linux:
	tar -zxvf cudd-2.4.2.tar.gz
	cd cudd-2.4.2; make
	cd cudd-2.4.2/obj; make
	cd NativeBDDSet/; make linux
	cd NativeHashSet/; make linux
	cd NativeMDDHybrid/; make linux
	cd NativeBinaryTree/; make all; make linux
	rm -r cudd-2.4.2
mac:
	tar -zxvf cudd-2.4.2.tar.gz
	cd cudd-2.4.2; cp MacMake makefile; make
	cd cudd-2.4.2/obj; make
	cd NativeBDDSet/; make mac
	cd NativeHashSet/; make mac
	cd NativeMDDHybrid/; make mac
	cd NativeBinaryTree/; make all; make mac
	rm -r cudd-2.4.2
clean:
	rm -f *.so
	rm -f *.jnilib
