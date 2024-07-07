# CryptographyEngineering-RabinPublicKeyEncryptionScheme
Rabin Public Key Encryption Scheme


Description:
This Java file implements the Rabin cryptosystem, which is a variant of public-key cryptography. 
The Rabin cryptosystem involves key generation, encryption, and decryption using mathematical operations based on modular arithmetic and the Chinese Remainder Theorem.

Key Components:
KeyGeneration(): Generates public and private keys (p, q, n) based on predefined prime numbers.
Encryption(n, plainText): Encrypts plaintext using the formula C = (plaintext^2) mod n.
Decryption(p, q, n, cipherText): Decrypts ciphertext to retrieve four possible plaintexts using the Chinese Remainder Theorem, involving modular square roots and coefficient calculations.
Modular Arithmetic Functions: Includes methods for modular exponentiation (modExp), computing Legendre symbols (LegendreSymbol), and finding modular square roots (modSqrt).

Result:
======PLAIN_TEXT======
plainText = 100

======Generate_Keys======
p = 7, q = 11, n = 77

=======Cipher_Text======
CipherText = 67

======Possible_Plain_Text======
p1 = 23
p2 = 54
p3 = 12
p4 = 65

Usage:
The main method demonstrates the complete process of generating keys, encrypting plaintext, decrypting ciphertext, and displaying possible plaintext outcomes.

Purpose:
Provides a practical implementation of the Rabin cryptosystem for educational or experimental purposes, showcasing fundamental cryptographic principles such as key management, encryption, and decryption. 
This was composed in 2020 for MSc in CS Security Engineering
