#!/bin/bash

echo '============================================================='
echo '$                                                           $'
echo '$                      Betc.io                              $'
echo '$  Test assignment                                          $'
echo '$  Mikhail Zotov                                            $'
echo '$  email:    zotov.mikhail85@gmail.com                      $'
echo '$                                                           $'
echo '============================================================='
echo '.'

docker pull mysql:8.0

docker pull redis:3.2.11

mvn clean package

docker build -t betc.io/random-customers .
