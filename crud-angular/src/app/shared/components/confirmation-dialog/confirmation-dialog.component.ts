import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogContent, MatDialogActions } from '@angular/material/dialog';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-confirmation-dialog',
    templateUrl: './confirmation-dialog.component.html',
    styleUrls: ['./confirmation-dialog.component.scss'],
    standalone: true,
    imports: [MatDialogContent, MatDialogActions, MatButton]
})
export class ConfirmationDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    // Injetando o material dialog data para a exibicao de uma mensagem de confirmacao
    @Inject(MAT_DIALOG_DATA) public data: string,
  ) {}

  ngOnInit(): void {
  }

  // Metodo onConfirm para retornar um boolean de confirmacao true/false para a acao desejada (podendo ser uma exclusao)
  onConfirm(result: boolean): void {
    this.dialogRef.close(result);
  }

}
