import { Injectable } from '@angular/core';
import { UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormUtilsService {

  constructor() { }

  // Método responsável por validar todos os campos do formGroup ou FormArray para iobtrer a instância desse objeto (UntypedFormGroup ou UntypedFormArray)
  validateAllFormFields(formGroup: UntypedFormGroup | UntypedFormArray) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);

      if (control instanceof UntypedFormControl) {
        // onlySelf: true = Apenas para marcar o registro selecionado e não todos no markAsTouched
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof UntypedFormGroup || control instanceof UntypedFormArray) {
        // onlySelf: true = Apenas para marcar o registro selecionado e não todos no markAsTouched
        control.markAsTouched({ onlySelf: true });
        this.validateAllFormFields(control)
      }
    })
  }

  // metodo para retorno de erro aos campos obrigatorios ou que contenham validacao
  getErrorMessage(formGroup: UntypedFormGroup, 
                  fieldName: string) {
    // Obtem o campo repassado
    const field = formGroup.get(fieldName) as UntypedFormControl;

    return  this.getErrorMessageFromField(field);
  }

   // metodo para retorno de erro aos campos obrigatorios ou que contenham validacao repasando apenas o field do formControl
   getErrorMessageFromField(field: UntypedFormControl) {

    // Validacao de campo obrigatorio
    if (field?.hasError('required')) {
      return 'esse campo obrigatório'
    }

    // Validacao de caracteres mínimos
    if (field?.hasError('minlength')) {
      // Caso exista erro acessa a propriedade minLength para obter o tamenho minimo de caracteres, 
      // senão retorna o valor padrão de 5
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`
    }

    // Validacao de caracteres máximos
    if (field?.hasError('maxlength')) {
      // Caso exista erro acessa a propriedade maxLength para obter o tamenho máximo de caracteres
      // senão retorna o valor padrão de 100
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 200;
      return `Tamanho máximo excedido de ${requiredLength} caracteres`
    }

    return 'campo inválido'
  }

  getFormArrayFieldErrorMessage(formGroup: UntypedFormGroup, 
                                formArrayName: string, 
                                fieldName: string, 
                                index: number) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;

    // Obtem o valor do controlerepassando o index do campo juntamente com o fieldName
    const field = formArray.controls[index].get(fieldName) as UntypedFormControl;

    return this.getErrorMessageFromField(field);
  }

  // Valida se o form está válido
  isFormArrayRequired(formGroup: UntypedFormGroup, 
                      formArrayName: string) {
    const formArray = formGroup.get(formArrayName) as UntypedFormArray;
    // Valida se o form está válido, com erro de "required" e se esta touched, ou seja foi clicado pelo usuario
    return !formArray.valid && formArray.hasError('required') && formArray.touched;
  }
}
