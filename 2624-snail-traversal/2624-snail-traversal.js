/**
 * @param {Array} arr
 * @param {number} rowsCount
 * @param {number} colsCount
 * @return {Array}
 */
Array.prototype.snail = function(rowsCount, colsCount) {

    if (this.length !== rowsCount * colsCount) {
        return [];
    }

    const res = Array.from(
        { length: rowsCount },
        () => Array(colsCount)
    );

    let idx = 0;

    for (let col = 0; col < colsCount; col++) {

        if (col % 2 === 0) {

            for (let row = 0; row < rowsCount; row++) {
                res[row][col] = this[idx++];
            }

        } else {

            for (let row = rowsCount - 1; row >= 0; row--) {
                res[row][col] = this[idx++];
            }

        }
    }

    return res;
};