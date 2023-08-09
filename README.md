<h1 align="center">
  Sistema de Bater Ponto
</h1>

API para registrar os pontos do funcionários, encaminhando um e-mail com as informações hora de entrada, almoço/retorno e saída se ele vor do tipo "mensalista". Agora se o funcionário vor do tipo "horista", o e-mail deve conter a hora de entrada, quantidade de horas trabalhas, salario do dia e o salario do mês até o momento. Objetivo é deixa os funcionários cientes e confortável, tendo o acesso do registro do seu ponto a qualquer momento, podendo consultar sua jornada diária, semanal e mensal a hora que quiser.


## Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Tecnologias
 
- JavaScript
- HTML/CSS
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Mysql](https://dev.mysql.com/downloads/)

## Recusrsos
Na pasta bancoDadosScript --> scriptDBA.txt, foi disponobilizado o script do banco de dados MySql, só copiar e colar dentro do SGBD e executar para criar o banco de dados com o nome "sistema_pontos".
Outra maneira é utilizar a exportação do banco de dados caso queira importar bancoDadosScript --> sistema_pontos_DBA.sql e importar no SGBD. Tambem foi disponibilizado o UML do banco.

## UML de Classes

[UML-Sistema-Pontos](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=uml-SistemaPonto.drawio#R7V1bk9q4Ev4t54GqyamC8h14nEuS3Wxmz2wmu8k%2BbXnAA04MJrbntr%2F%2BSLZkZKltC%2FAtiba2KmNhDEhft1p9%2BXpkXm6e30bubn0dLr1gZGjL55F5NTIMY2ZM0T945CUbceZONrCK%2FGU2pO8Hbv1%2FvWxwRgYf%2FKUXF%2B5LwjBI%2FF1xcBFut94iKYy5URQ%2BFW%2B7D4Pih%2B7clScM3C7cQBz95C%2BTNfly9Ffh8V88f7Wmn6w78%2ByVjUtv1rKBeO0uwydmyHw9Mi%2BjMEyyvzbPl16A545Oy6dfXz4F7786b9%2F9EX9z%2F7z47ePvf42zh7055C35T4i8bXL0o927fz5PL%2F%2FWLpOrp7dO%2BM779ua3sUlW6dENHsiEkR%2BbvNAZ9LbLc7wQ6CrceduRebF047WHH6uji3WyCcifcRKFX%2FNpxiNR%2BLBdprdq6Ao96Y0fBPsrghbdSN%2FsRgkZmOGXn%2F3kM751Ytjk8m%2F6TvT31TN78ZI%2FM4lesndZ0xkdwO%2FTJ9p0Tgf2b06vCu%2B%2B8SJ%2F4yVeRAYlJ58sUhw%2BRAsycZ8vLv7c%2FP7HH9%2B%2BrP%2BJf3%2F07vXP4XhmESFwo5WXVCyNQcTMWxYAThb3rReiLxm9oBue9rC2CVTXDKLpWOQFbuI%2FFsXCJdK1yh%2BXf8JN6KMfbGhEEYyJWBAtoJtoVQqPyH45eReLQ%2F5BjqYVHjWztOKTsqkRnoRA6L4wt%2B3wDTF7B%2FqDmZn9UCoJsFR8%2FMtfvfsr%2FHPluF8%2FjONQ85I5ESRWKG7CbRIKkhE%2F%2BZvA3aKri3t0wy15BQPfDfwVEpWrBUINhtLFoxclPlJL5%2BSFJNyh0cXaD5bv3ZfwAa8WEoHFV3p1sQ4j%2F1%2F0WHcvXnsJQehg77jF7ySAjbwY3XNDAatzQ9fuc%2BHG926ckIFFGATuLvbv8p%2BxQUvhby%2FCJAk35KantZ94tzs3RfkT2jM4HUDmBAv6ZRiEWIyW3r37ECQHCxOeM%2B%2B5EvzkVdvkMEUuGdnIx1jhsJxZuSAUYHUohnQBQ2PylnP07%2FtwuxLghH5rkmtROnfbMMMXM51kiEIs8O6TUoDFaKH87ep9es%2BVtR%2F5QCYBD4XovfdBquHX%2FnKZ6vgoTNzEzZCAl51IG%2Fqi9gX6H83lpTaxRzb64pfoWt9fo%2F%2Fx7VFyGW7Rb3H9dKk9BLMnD0OtDkEiRiqFtB4jBBNUodZhgt7XOCTEvRZDAgmx%2BxptPO7SpdhAq%2FgR7UEKIN0CxDZ6BogOKw2MkFvXV%2FjoGR%2FTWd%2F4MEvxEX%2BM3Ds3WCst0jdKdM3qGyYGCJOl7%2BYgCTOQ3KGTuOduFUQ6hogpaZ62BxHxiJNCBE10igzH3eC52N7F%2BJ9Ul1y5idIlXQPF7ttmnYM42aKnv3nYLvxw60Y%2BUSa3SeSrU03nEJl1aLWCHi3dAjESuwHGxpVPDJILf3XlLfyNGyiIdHzw1To0XGGIOFUQucbxAQWRXiFidmi0whCxQYjc87sMu%2B0okHQLEqdDsxUGCexCUyAZEEhmHZqsMEhgmzUJMwP17JUyV%2FuDh0mdE%2F3BYyrAY2Sg36u99ZLEi2KFiI4RYfbtVi0DxK0CRC%2BAcPp2oIr%2BUwEDgZ%2BurZjxcjgANmgpA2%2B%2F4h8xIK7GuoAKU0SFCSAgcO%2B84CaM%2FQSZQGgsyu7lkFG3%2BEV0%2B9u1F%2FlJm6s%2BlzQu2wrYi7GVTAukqR9nv24Tb%2BVF6UNejQwnzWa4Q9fOaj8rSkd0piPmfW8agNeLwQv692xUjO2jqWVDuellMSSTDrFnmXSA87W%2BUlDrFmqWIbkdWVZbmkliP1pF4cNOnIKq7CqScUsWa5TnucpnXel8Jh%2BgwufATNlWa1lXohepyhegEvgOTeBb4G%2BLbB%2BaHVtM8F0FbhyTv%2FN0aTiJthrp9RCsk8ZKjJHHfcDw367QZBWep%2BvA82jIlj7ODRAstm7iXeAZaCUNtcRrvnGTyF88BCSuklsmaluA4MwDVhKJuSL5ftILAccGjdUqj9eAgNJ%2FmiHsRY%2FDO%2FRbFVqGhZb%2Bkw5hdzqJ3arA7cDw0mn6Iehgh7chb%2BP6gdIsQ0JKl1mIcC0ciJQFOrK4Vz7SLiqVeWiQ6T0fMXdSAJi5RTpm6wbqWDQoyHSZnwhDBq6qSSFz7W1jhZhhIabTdEUYMaLDNc0g8Xchmjw0d4lKeR4UYrrMXoQRAxu9uJ5CrO1UlRRDwEyXyYwwZuBqvl0aPwSKcHw0f3Qs9cWTSCMZUnHoQcCq9%2FRHOGiw9NCPTdyNR8GlXbEjCjb9wkY%2BLbKByCmcFlmW%2F6LyZoeJGEt2%2F2oLMaJfWOXRDgohjuw5qjWdIjprVGbtoCAyl7VW2krYMaA0Og4VP1mubXmyTWs4kM5xaw0GYt5WpimY7C0mBzdPgVHZkH2rEIvmbPVX0CN660TspHkNaT7tPs0hvcziktkrJKSdXtAcb7TS2wLCnG8PYb4A4zhbAWwY7yIvnWh6wz43HP1yc579RVjw3mSPzW5gA17pZxeiGfsR4qzu%2BusV3J5Z2jLj1soGmLOkEsneRdKWNPwcoy11LjohRJEsJOayee8KQX0jyDY6PFzCmcCHEROThWfmimceprzAI4YTGC9LDSMwISHWRwwD8YTyEcMcxOKqnEQYTCILLF9wJevXQPiC83quPEbDJVDL8gVbVs2DSuiCm8rCNkU%2FB106Bopo0t%2Fjs0ARhLVFBPl5JE3lL1UqWG8UCxTkJJ9K0emlHmNtMnMsvbAStGTuRKQY4EPp%2B8P7%2B9hrZWEtyDtBCvuo7aNPJv9lzKK70nq%2FDgDQ%2BZqjJbdmxdXRG1lxqtM6WGMx96My7qEKgobC6D02jaLel6X0NvSKgyZQ%2BJN%2F3qz4eYB%2FXTeAz%2BuhMMgST7wsubhKZGqiUNSSh%2BxQKoAsOKNA1XT0DIzeK34smKJR5eT3jYzeq3ssmHYPGRI7NFXuo6eSY3sFSP9s4hacUJR4aA3TRVXqoz909E4kbtfSdhJuVzF9seD%2FVUmMfcKo97IeG%2FLFcCj4ydIADqbcOmLdZWtz2vLy26J7JosTsU4anntLKYdulYMsMVt7ygFKTT0%2BFiTOVJXHaiDhlDEXBdHnnA9VNpzCczYJD2o5nGKL5uR3E06hQGwonFLsqNlQNAUO0bTvabfFCqgsePKzLa02mZqzE%2BMkjS4XGFU2JQyuXjjlxjOulaeuz8UjsOlAGxDfSLaxFDOg6rw2AQujcPxEvt556oaNMEEKmIN1aYzOL3wM%2FXu8waPLiwuIZbRshEvZytK1FomwpD3Gtxxt9D3Gtzbho7t%2FOqt8dM7ew9dLL%2FDYa2%2Fps5dBuPia2yFFWZHL76mW5YNY80ARyo29HyJ4BssyTAeggmcNpaCdClEaMxFdErDSbyutmG6IwtkULSNOPY9us3ThmzDCPa4M7YxlwYKTiQuKu1Ddm91UGMpMASHzl2HD4Tt%2Fxq8UHdfAYAx4WEAYN8GIC8O4jKfavYv8KCerBmsrUjCRWxSOesUR5OnvWB%2BWlejce4u1G%2FE0%2BQpGg4RR%2F9sq1G1FefozT393OAAMdPhAoDVwpl5cfPrwxvnoX3%2B9%2BuXLW%2F9Zv328HtMdj%2FMN0SMpmr11uAq3bvB6P8p5cvf3vA%2FTdcXL%2BsVLkhdy5nQfMNsFK2l5IUB%2BIVcIgIyrdfq5Orm4cXEJM4YA%2Bh3aLH1D7oIOd6lCQSP4UFlYTM6f3GChgElcWGylAEy76MDYkPZWnaQATNGb%2F3qzC8IXTyRFUum4g0nHnemcW84C2jSZ0Bnfdo70KXAhjznA2DQUp4IJJtgVz3OpBfTB2%2BHNI0zndseNiKRPu8h%2FTNnCtHs%2FLQYVTonKpILFRHbrNKUloTzLF4S90UA7GPArw9nfRVQwuTUFxOG8HAW47wtwNmCqdQs4MK28iIrXOJX41ose%2FQUGj8deKpR9ByibSu7mraHMLPMvgP7WgnuV95HWulYHMaSct0OTAaCpICgDdAduXgbKeBP2zlrlYhsYaEBXCqg523KpmSUNjIvqRrlpB4whR%2FZQ0RaGSirE3OQBLea%2FFDc0XJRxFIPhyLS7KQHTY4g%2BU2GpYyzNgZBjW1iCPbtTYdE78Ox246GtXKRaF611qucVzvm0HG4pm8vhrfgZ1QdC%2FhrZ5o9UjbA6w2N5xI7Kk2CfFue9lYVvU52td%2Bcuvq5SDI4XmQLCCszf%2BolfkrNXSl8l6MXOjhDFPRzMDFTquGt3oS55qKBF4qeoY%2Ffun8%2FTy7%2B1y%2BTq6a0TvvO%2BvfltbIMN%2FARs0WP2LwhTe0id8fs7y%2FvX9wm6Rhnw5oiybXsRgKnk%2Bai17G0L7DOnBEAJQCcCYOq9C0CZa3WDfaYrb5N678%2F3PqZq26Y98BbOl%2BoQ2SNmZYNO7WFWyqlVAPAbxsPVPIIVNoeCTaAguVtsguS0rD5NN906DB5%2B0oUgqJhXegGhBeVjdwvCsv47rB17HmzCRXi%2BXcogUuPwpTDVMab6PylJVAX%2FtMnZra27LXtAsNtad4Bhg2VgYVPIODSoDN1DM3Tpq%2B9TOQKrhhcBEp%2BKKmOeAkFEZkNJvxbHwatDZ9ncwVOoG6jC6ndXSPzkG7%2FenOsPQfz24irYLb%2F97%2FxvuLKuJszAxBai1d2Zlu279J9XmdmXMgfcuxs%2FyDI0tV%2B84NHDosO8HqdCgF%2FVjd0z%2B0L2oRzjQPbaI%2B4xg%2F5FAugmDwhGNfctkCiU3MIyG1hahkItSPu6jYmqFt8ZRru1uyWPNLIxbFWMia44T59xnzCv%2BNtlimr8kkZ%2FaPoK0t3b%2BB49nX5OqoUyrIRPxQ95CqNl8Wvlz0K%2F5O6rjx6Hn5np%2FjFBm%2FiZS2%2BBrCq8vYyTtb%2F4uvXiWAwVCfcya1J5X0noSUPGj5vwP3Ppx7vAfaG3p%2Fuyof3H3%2BDNzd2SSYQDWxh8Bubuw7hj%2F8ggCPQI4s4m6bdCa4NV58ULY1f%2BL1p60cXLFVJYV168OOqcg4aqusMcHeRqxrCzm0gZB5VmE3YdrKqgA2uNqmpKVgFpVKLEiVJ16BzLWZmMcXLSYVswUSQlO5R%2FFyILJGS1JrJwG291FDv5KHb4suua7Lq3lQY%2FE63KkmIedRL7iU5iunASA5xFOkTL%2BUMxOoHWDe1AyohMtmVe0%2BTmioTnwv7Z5h6UC%2FYpZiO4wmZrugiKG6s96KA96PBlh8qcwWVvy%2FSYiemdQu2y2nx%2Bns1nNlNbT5moKDab0zWkIw3IAzdGp7WNEfCnHEjhSgfe7VxWr0q0mmRUDKSB2jNBaXViXrSkSeqB1uJWc1H8kMnmUWEvIYi%2FwzSsI65ihFXcgqTyzYTZohHaIlibWOZsxLQJHlU1CcYXN0hO0DzkbE%2FouWRP0w1uk3MEMdvvJF8eNju64eYurUMwIM0sNBwufO4wZM04SZdlwx87U%2F5cxT%2BquVoa8PByWJ%2FrReDGsb8o4rUMnXoRU9aI7YM9sVNvbE6BpU90M%2BfEKuuGja8E1PbfIpvmY9X2yKYbx2CAzJtWc4NTldJtHbh22zP%2BQSUwRsByX5jbyM5f%2BpVzkrj8G%2BtW5RcT3kAjw7JvmFnV9xtcV2rufvRH9huPlVqwpoa6%2F7omr6uuX5QogYzX7jJ9wGliCE6KJkphZflgrRQ2TkhX9bX5025OZqOOukPlpNN1ysSRKxfAJAWbFVlVhMtVZ1NOO9nDOJyCwIaJ7pm4HUvQxDpCy6CvEkBLHL6VemWQ%2FaPBbyz60bMuwYSdM4VJKVenAker4OiyhzT4jeHe4hCFpVbnLVZQaRUqXTaVhq1jASoQH1Eaovt1ybV3VIWqfcGm01bT8P4jVfP3sFsis6kAG7B8RSGpNyR12ZYaRlJZhV7krXw8VbVEaKqurhfkdNmJGjZzRHcAtnNijymPLyiac3zW98OI4OYuDAMP5zUq4HQKHNlW1u2pHJBmBwGHLUtXyBkccrBftGeVAztqGM7X%2FaF7fxpPTZ58%2F%2BqUYybfK3tmWntVlkyu5KlHeTIljxFWa%2FIE%2B7FYAtx6gdKKINcOKZ3nFLsyJXvB4UzyEFLVnuY04j0Qhhyd%2FbUXny8eNmhkWcdaUwRZalYsQ8Ua3zPOTCACBOu7tnCmchAPyUFsaNVltUsDfV3Bbyw6ONio9RkUzYNCf6leKd0Na1tWKZKYrrXNTPK00tquJh5zWaoQlTUx%2BKyJqVNMOnPmkjWPBp8a%2BV3n88MpE7D37x6swOTTJlTEsyGtOJfG9IEEC625cHTRhbPnyDgPgrNXQBc0sV6%2BYP8Xq%2BYVoLoEFJRt0S2gRB%2BGXAydiWMpzHSKGSjtolvMiCk6JZjBGilLot65K2LrZ9fYzIF01U16n9JVA8QdmLfRLfCkEn5i99GrpXWSKMdXOGoJR1DWRqc4ArpoZ1aUkPIj65xXqOodVVBGR1uMOrB2Kut%2BkNbbg6hSgZs%2BD3NQDUe3%2BxnUhlV51A%2FyqB%2B%2B7LKH%2BLY86npJ9ilQGXNW4vWBHUXqJNa1BoFSECAoNUFMAGNJDM9I1FcpJ%2FlgnOR5fSA93ENt%2FBxwUzLKMfWDOMklGgouYfr41KySpJZXSvIQJZlrnFOc5jCi2zLMDTHYUuGvkvOgs9hSbqle8QW1EIPw1QSPHYyvkqBMO%2F5QhbzBIA%2FIzSnZq9tC3rHRm7wCsogmhZ9O8QOxQHcMoJJQjpY60Yvg0FhbS501OwcLTTfvDSwm5K4C3ea0UrYSQEr%2F9A4pW%2FKwSaHXPKSqfeboD2jTUv7yfuACNUTtdrtSROEnO8yPOMDrkmZKWx5zo2TjKZq5QHrwWZkLSIP9Rsqq6dw5ZIgudHgLagtcpkRZi0iI2BCnbzkTL6X4zUhUDcpzKsH4y1KvTkcF6tU9FeshzKvi4p%2FEokq75bD8jZWBsqGwqOYepJwFcMq5lKTpgG2ahZCTiXLwPphGtWnG%2BblEbBqXV%2B%2BoXsq2tDEm5j4UMotwu8Whijv6YPlylTyswZOGW46oWCyaOlkwVqb6pAnlAs8i5HrmZlFUGaw%2BWbs7fN%2FmeYWU8nqCtwRcXpxM3O2WTPs%2FhrjzVJoaXvT60ct2Dp1bvjlePfRRSx%2FdAOx3NFZISGMLarCMOHxH9cktbYxZolaqgSgNBsM0J1SrEziM0VFnYpkCIpAIT2a6CAqLsmw0jwjIWcwbHbgr6iq1PGv2ZXbpcOS5zVmdmZO5zSlA0SWhOxNnaln6zNEsx9Z12uCuIHL26ZMLkpyDCTVcI4ScuF%2BiBQKx%2FpgJr42P52cGvmuKaORjGYrJ9n%2BAjcmsecsLrk1MZ77%2Fr7j4U3HtodSHJqIui4tPH944H%2F3rr1e%2FfHnrP%2Bu3j9djS%2BIsyNhtZPKOt9uIVYWMKM1krSptYtlHEdqDxs0BK1nLf51tPn2ZS2hfLeDF5oRe1lYyOMZ5PkzcXN8EmFxhJqKqCwZ2asQXoTY9BmgSdO2H2Wu1DO20rKmeol3WxO%2BGop1%2Bb0anvN64vsh1oTKofuA%2BZGPdNLh%2BGVOgWZ5J62FY5alX1cP%2FGGlZNsjQXPTFeVhqbtH26xHerXfuo3u9HysxtJrxQuVSfFKKErS6raWQ2HKVTWjyUn10RiMgWkIoe%2FKB%2BOHuC8IXN4rnlwRPHkMcnRvW%2FAO1G%2BD8t0ZtY6vAwsmBhcOXHSCEAJe9tcCCLUUE%2FTpTZllEgVdkGqPqSsMHfcmVDnnXQcXWQMQOPKXRlm7DamC0d6wf5VfXbe4EqKVGdBsnwCqLpt6BPjvRmC7xXs85Zys6ok2mx3bGM%2Bdm4WFT6lRquKOY8K2Js0j6ixXvP7nfFyguonOQKedAehtp6CAA7Cd1HjmuokNQpbLSV3F4yL2iuRIGsmyoLi9sc%2FMje0UN8uwA4ls8XwtHB9U3qplgfQ20y9XPQackEMetGRMy1lpVHQd%2F%2FcGLd2iOvdfbxE9einn2B7EmgVf5hyqYdglTgFOpW5hKtyU6vBwEbPRdR6NehfKD%2BHZUsUl%2FqAZYn0BUm1ZLqDbFUuODmcLSjbwKjorsaSBwg0L7HW%2F2YvXvkWRP8tpQwW8o8DNlz0xtwe9oMp8qfFF%2FvIJWj9ACEgO7hZZ0GRXX%2F6bpfjeA6XiQJgWNUbBPzmk2ar1Fqk5a%2FYgSVBAEiZLdQAAH%2FH6QTcph4CcP27XgBrIk9%2Ba2Fr3EMNRKXOWabJMKVf3TtUMRivtDSLKMlqBE07y7DU%2Bm4RMuMTUdK5YGVYYwR6fFDtkUPnjliGqvDTJmzZQ7yNir%2FJp8fxoVK%2FsuYmUWrSzPGVUAi8KC8uyqdpcfI1YG9HoQgmX89Y7pzcQ0JlcBs7b2t5IU%2B4qIGYTm9pxoZVyjsv3J5U9pyrcxAMBBsa9OAQeS26oT2cknsuplh4JD0LK3dSIDaGgzNcPZYmfF3Uhjtyt1%2BupaWcwkd6fWTl%2B0IXlftVVZ9meexjmqS%2BHs%2BUQ2lzyRzXs9kIneGZaERZ3LvodzWd6qMU9hBFol0Er2wjGpqkLmxziWAWzClazU%2BW7XfPxGYNDjPksIw6gNtuENtkTVVrSLhKSmNWu8jJmYaxdZGbM%2BjPtanf56xBvUTbJbvBnVeMPZhgdnPAqxYjXUWLKnvCjX550qYe9U2KE2oJCwtxc7k0rHJ8Jfwj%2Fet7SooZaGqtRQpdKpVTNK7fSqdqSPgk3U6sN6R7p7pzQNeQd7r8ROqyDdE6QBcpduIU0ffEQbBrWLnih8Sti69YlAXVe73T9qUvtHlnbjJuu%2F3MhPp97QinYrgp4SuuPkUOUIDEECAed6xxKo%2Bh63kiRQs%2B5AuzJw3dvKEjDKkpHgAOEZGNnQgNiKSh3oXIfM%2BvY%2BWb1kbjO5A44zH3EkUM6oHXbWljMPallaZxYMhlMZgmc8y6YlRyJ1KPWTSOdJPqiM%2B0l4g0lIkYU3lNzQDjsUwLwpCMHiIXrM4STyXxe440GCa62IarsO0zmV2pTlUtNH1VxqLHwL5RAE4Hw5xIkQr21KMgNItSsyakVZ6KoHCT0%2B5b2ZZscSr%2BnWjMvy0HQuMNgQ9Zpp8F%2BasAuUf7f8q%2BRvMUokcP9zuKQVQ7NrGN50m6eE004jeUOXUYhzNPa3464d1yE6WqLB%2FwM%3D)
Pode não estar certo, correto ou perfeito, mas eu tentei.

## Como Executar

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/).
Tambem foi utilizado uma aplicação Front-End consumindo os ENDPOINT disponibilizado pela API, usando o Fetch API do JavaScript
e utilizando HTML/CSS para fornecer uma interface para o usuario.
Se desejar utilizar uma interface abra a pasta front-SystemPonto, no Visual Stuido Code, baixe a extensao Live serve e abra uma
guia HTML utilizando o "Live Server", é necessario estar conectado na internet, por causa que está sendo utilizado a biblioteca do 
[Fontawesome](https://fontawesome.com/start) e o [SweetAlert2](https://sweetalert2.github.io/), necessitando de internet para funcionar.

- ponto-controller
```

POST
/pontos
```
- funcionario-controller
```
GET
/funcionarios

POST
/funcionarios

POST
/funcionarios/atualizar/{matricula}

GET
/funcionarios/{page}/{size}

GET
/funcionarios/{matricula}

DELETE
/funcionarios/excluir/{matricula}
```

- departamento-controller

```
POST
/departamentos/salvar

POST
/departamentos/atualizar/{id}

GET
/departamentos

GET
/departamentos/{page}/{size}

GET
/departamentos/{id}

DELETE
/departamentos/excluir/{id}
```

