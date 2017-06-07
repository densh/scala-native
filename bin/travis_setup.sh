#!/usr/bin/env bash

if [ "$(uname)" == "Darwin" ]; then

    # scala native dependencies
    brew update
    brew install sbt bdw-gc re2 llvm@4

    # extra configuration for CI
    brew jq
    export PATH="/usr/local/opt/llvm@4/bin:$PATH"

else

    # scala native dependencies
    sudo apt-get -qq update
    sudo apt-get install -y \
      clang++-3.8 \
      libgc-dev \
      libunwind8-dev

    # available as libre2-dev package starting from 16.04
    sudo apt-get install -y make
    export CXX=clang++-3.8
    git clone https://code.googlesource.com/re2
    pushd re2
    git checkout 2017-03-01
    make -j4 test
    sudo make install prefix=/usr
    make testinstall prefix=/usr
    popd

fi
