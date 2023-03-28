using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace GestionObras.Api.Migrations
{
    /// <inheritdoc />
    public partial class Inicial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Adelantos",
                columns: table => new
                {
                    AdelantoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: false),
                    PersonaId = table.Column<int>(type: "INTEGER", nullable: false),
                    Monto = table.Column<double>(type: "REAL", nullable: false),
                    Balance = table.Column<double>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Adelantos", x => x.AdelantoId);
                });

            migrationBuilder.CreateTable(
                name: "Personas",
                columns: table => new
                {
                    PersonaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombres = table.Column<string>(type: "TEXT", nullable: true),
                    Telefono = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Personas", x => x.PersonaId);
                });

            migrationBuilder.CreateTable(
                name: "Proyectos",
                columns: table => new
                {
                    ProyectoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Descripcion = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Proyectos", x => x.ProyectoId);
                });

            migrationBuilder.CreateTable(
                name: "TiposTrabajos",
                columns: table => new
                {
                    TipoTrabajoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true),
                    precio = table.Column<double>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TiposTrabajos", x => x.TipoTrabajoId);
                });

            migrationBuilder.CreateTable(
                name: "Pagos",
                columns: table => new
                {
                    PagoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: false),
                    PersonaId = table.Column<string>(type: "TEXT", nullable: true),
                    Monto = table.Column<double>(type: "REAL", nullable: false),
                    AdelantosAdelantoId = table.Column<int>(type: "INTEGER", nullable: true),
                    Total = table.Column<double>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Pagos", x => x.PagoId);
                    table.ForeignKey(
                        name: "FK_Pagos_Adelantos_AdelantosAdelantoId",
                        column: x => x.AdelantosAdelantoId,
                        principalTable: "Adelantos",
                        principalColumn: "AdelantoId");
                });

            migrationBuilder.CreateTable(
                name: "Nominas",
                columns: table => new
                {
                    NominaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: false),
                    ProyectoId = table.Column<int>(type: "INTEGER", nullable: false),
                    Total = table.Column<double>(type: "REAL", nullable: false),
                    Estado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Nominas", x => x.NominaId);
                    table.ForeignKey(
                        name: "FK_Nominas_Proyectos_ProyectoId",
                        column: x => x.ProyectoId,
                        principalTable: "Proyectos",
                        principalColumn: "ProyectoId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "NominasDetalle",
                columns: table => new
                {
                    Id = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    NominaId = table.Column<int>(type: "INTEGER", nullable: false),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: false),
                    TipoTrabajo = table.Column<string>(type: "TEXT", nullable: true),
                    Cantidad = table.Column<double>(type: "REAL", nullable: false),
                    Precio = table.Column<double>(type: "REAL", nullable: false),
                    PersonaId = table.Column<int>(type: "INTEGER", nullable: false),
                    Balance = table.Column<double>(type: "REAL", nullable: false),
                    personasPersonaId = table.Column<int>(type: "INTEGER", nullable: false),
                    tiposTrabajosTipoTrabajoId = table.Column<int>(type: "INTEGER", nullable: false),
                    proyectosProyectoId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_NominasDetalle", x => x.Id);
                    table.ForeignKey(
                        name: "FK_NominasDetalle_Nominas_NominaId",
                        column: x => x.NominaId,
                        principalTable: "Nominas",
                        principalColumn: "NominaId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_NominasDetalle_Personas_personasPersonaId",
                        column: x => x.personasPersonaId,
                        principalTable: "Personas",
                        principalColumn: "PersonaId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_NominasDetalle_Proyectos_proyectosProyectoId",
                        column: x => x.proyectosProyectoId,
                        principalTable: "Proyectos",
                        principalColumn: "ProyectoId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_NominasDetalle_TiposTrabajos_tiposTrabajosTipoTrabajoId",
                        column: x => x.tiposTrabajosTipoTrabajoId,
                        principalTable: "TiposTrabajos",
                        principalColumn: "TipoTrabajoId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Personas",
                columns: new[] { "PersonaId", "Nombres", "Telefono" },
                values: new object[,]
                {
                    { 1, "Manuel", "829-811-4569" },
                    { 2, "Samuel", "829-846-5619" },
                    { 3, "Juan", "809-578-1978" },
                    { 4, "Ana", "849-678-6719" },
                    { 5, "Josefa", "849-789-1290" }
                });

            migrationBuilder.InsertData(
                table: "Proyectos",
                columns: new[] { "ProyectoId", "Descripcion" },
                values: new object[] { 1, "Casa de Enel" });

            migrationBuilder.InsertData(
                table: "TiposTrabajos",
                columns: new[] { "TipoTrabajoId", "descripcion", "precio" },
                values: new object[,]
                {
                    { 1, "Carpinteria x dia", 2000.0 },
                    { 2, "Ayudante Carpintero", 1000.0 }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Nominas_ProyectoId",
                table: "Nominas",
                column: "ProyectoId");

            migrationBuilder.CreateIndex(
                name: "IX_NominasDetalle_NominaId",
                table: "NominasDetalle",
                column: "NominaId");

            migrationBuilder.CreateIndex(
                name: "IX_NominasDetalle_personasPersonaId",
                table: "NominasDetalle",
                column: "personasPersonaId");

            migrationBuilder.CreateIndex(
                name: "IX_NominasDetalle_proyectosProyectoId",
                table: "NominasDetalle",
                column: "proyectosProyectoId");

            migrationBuilder.CreateIndex(
                name: "IX_NominasDetalle_tiposTrabajosTipoTrabajoId",
                table: "NominasDetalle",
                column: "tiposTrabajosTipoTrabajoId");

            migrationBuilder.CreateIndex(
                name: "IX_Pagos_AdelantosAdelantoId",
                table: "Pagos",
                column: "AdelantosAdelantoId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "NominasDetalle");

            migrationBuilder.DropTable(
                name: "Pagos");

            migrationBuilder.DropTable(
                name: "Nominas");

            migrationBuilder.DropTable(
                name: "Personas");

            migrationBuilder.DropTable(
                name: "TiposTrabajos");

            migrationBuilder.DropTable(
                name: "Adelantos");

            migrationBuilder.DropTable(
                name: "Proyectos");
        }
    }
}
