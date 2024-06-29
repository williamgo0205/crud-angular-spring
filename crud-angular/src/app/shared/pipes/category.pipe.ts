import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'category',
    standalone: true
})
// Classe pipe recebe um valor e transforma esse valor no que desejamos
export class CategoryPipe implements PipeTransform {

  transform(value: string): string {
    // Recebemos a categoria e retornamos um valor
    // Nesse caso para o front-end retornamos o valor code (icone code)
    // e caso seja back-end retornamos o valor computer (icone computer)
    // link: https://fonts.google.com/icons
    switch(value) {
      case 'front-end' : return 'code'
      case 'back-end' : return 'computer'
    }
    return 'code';
  }

}
