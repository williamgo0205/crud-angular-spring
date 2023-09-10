import { Lesson } from './lesson';
export interface Course {
    _id: string;
    name: string;
    category: string;
    lessons?: Lesson[]; // o interrogacao "?" faz com que o campo não seja obrigatorio, esse e o operador "Elvis"
}
