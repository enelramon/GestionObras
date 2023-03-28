using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace GestionObras.Api.Migrations
{
    /// <inheritdoc />
    public partial class Segunda : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Pagos_Adelantos_AdelantosAdelantoId",
                table: "Pagos");

            migrationBuilder.DropIndex(
                name: "IX_Pagos_AdelantosAdelantoId",
                table: "Pagos");

            migrationBuilder.DropColumn(
                name: "AdelantosAdelantoId",
                table: "Pagos");

            migrationBuilder.AddColumn<int>(
                name: "AdelantoId",
                table: "Pagos",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "AdelantoId",
                table: "Pagos");

            migrationBuilder.AddColumn<int>(
                name: "AdelantosAdelantoId",
                table: "Pagos",
                type: "INTEGER",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Pagos_AdelantosAdelantoId",
                table: "Pagos",
                column: "AdelantosAdelantoId");

            migrationBuilder.AddForeignKey(
                name: "FK_Pagos_Adelantos_AdelantosAdelantoId",
                table: "Pagos",
                column: "AdelantosAdelantoId",
                principalTable: "Adelantos",
                principalColumn: "AdelantoId");
        }
    }
}
