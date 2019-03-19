
/** 
 * Clase para realizar consultas a la base de datos
 * mediante filtros 
*/
export class Filter {
    openBrackets: number;
    closedBrackets: number;
    operador: string;
    campo: string;
    condicion: string;
    valor: any;
    type: string;
    modelAtr: string;
    joinTable: string;
    typeJoin: string;

    constructor(openBrackets: number,
        closedBrackets: number,
        operador: string,
        campo: string,
        condicion: string,
        valor: any,
        type?: string,
        modelAtr?: string,
        joinTable?: string,
        typeJoin?: string) {
        this.openBrackets = openBrackets;
        this.closedBrackets = closedBrackets;
        this.operador = operador;
        this.campo = campo;
        this.condicion = condicion;
        this.valor = valor;
        this.type = type;
        this.modelAtr = modelAtr;
        this.joinTable = joinTable;
        this.typeJoin = typeJoin;
    }
} 